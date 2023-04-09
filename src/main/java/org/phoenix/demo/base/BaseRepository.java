package org.phoenix.demo.base;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity, PK> extends JpaRepository<E, PK>,
    JpaSpecificationExecutor<E> {

  void saveInBatch(Iterable<E> entities);

  EntityManager getEntityManager();

}
