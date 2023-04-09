package org.phoenix.demo.base;

import jakarta.persistence.EntityManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.hibernate.Session;
import org.hibernate.dialect.Dialect;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class SimpleBaseRepository<E extends BaseEntity, PK> extends
    SimpleJpaRepository<E, PK> implements BaseRepository<E, PK> {

  private static final Logger logger = Logger.getLogger(SimpleBaseRepository.class.getName());
  private final EntityManager entityManager;

  public SimpleBaseRepository(
      JpaEntityInformation<E, ?> entityInformation,
      EntityManager entityManager) {
    super(entityInformation, entityManager);
    this.entityManager = entityManager;
  }


  private static int getBatchSize() {
    int batchsize = 300; // default batch size

    Properties configuration = new Properties();
    try (InputStream inputStream =
        SimpleBaseRepository.class.getClassLoader()
            .getResourceAsStream("application.properties")) {
      configuration.load(inputStream);
    } catch (IOException ex) {
      logger.log(
          Level.SEVERE, "Cannot fetch batch size. Using further Dialect.DEFAULT_BATCH_SIZE{0}",
          ex);
      return batchsize;
    }

    String batchsizestr =
        configuration.getProperty("spring.jpa.properties.hibernate.jdbc.batch_size");
    if (batchsizestr != null) {
      batchsize = Integer.parseInt(batchsizestr);
    }

    return batchsize;
  }

  @Override
  public void saveInBatch(Iterable<E> entities) {
    if (entities == null) {
      throw new IllegalArgumentException("The given Iterable of entities cannot be null!");
    }
    Session session = entityManager.unwrap(Session.class);

    LinkedHashMap<Boolean, List<E>> partionedByNew =
        StreamSupport.stream(entities.spliterator(), false)
            .sorted(Comparator.comparing(BaseEntity::isNewEntity, Comparator.reverseOrder()))
            .collect(
                Collectors.groupingBy(
                    BaseEntity::isNewEntity, LinkedHashMap::new, Collectors.toList()));

    int batchSize = getBatchSize();
    for (Collection<E> pEntities : partionedByNew.values()) {
      int i = 0;
      for (E entity : pEntities) {
        if (entity.isNewEntity()) {
          entityManager.persist(entity);
        } else {
          session.update(entity);
        }

        i++;
        // Flush a batch of inserts and release memory
        if (i % batchSize == 0 && i > 0) {
          logger.log(Level.INFO, "Flushing the EntityManager containing {0} entities ...", i);

          entityManager.flush();
          entityManager.clear();
          i = 0;
        }
      }

      if (i > 0) {
        logger.log(Level.INFO, "Flushing the remaining {0} entities ...", i);
        entityManager.flush();
        entityManager.clear();
      }
    }
  }

  @Override
  public EntityManager getEntityManager() {
    return this.entityManager;
  }
}