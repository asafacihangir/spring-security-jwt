package org.phoenix.demo.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonIgnore
  @Version
  @Column(name = "VERSION")
  private int version;

  @JsonIgnore
  @CreatedBy
  @Column(name = "CREATED_BY", updatable = false)
  private String createdBy;

  @JsonIgnore
  @CreatedDate
  @Column(name = "CREATED_DATE", updatable = false)
  private LocalDateTime createdDate;

  @JsonIgnore
  @LastModifiedBy
  @Column(name = "LAST_MODIFIED_BY")
  private String lastModifiedBy;

  @JsonIgnore
  @LastModifiedDate
  @Column(name = "LAST_MODIFIED_DATE")
  private LocalDateTime lastModifiedDate;

  @JsonIgnore
  @Column(name = "DELETED_DATE")
  private LocalDateTime deletedDate;

  @JsonIgnore
  @Column(name = "DELETED")
  private boolean deleted = false;


  @Transient
  private boolean newEntity = true;

  @JsonIgnore
  @Column(name = "RANDOM_VALUE")
  private BigDecimal randomValue = BigDecimal.valueOf(Math.random());

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public String getLastModifiedBy() {
    return lastModifiedBy;
  }

  public void setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public LocalDateTime getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public LocalDateTime getDeletedDate() {
    return deletedDate;
  }

  public void setDeletedDate(LocalDateTime deletedDate) {
    this.deletedDate = deletedDate;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }


  public boolean isNewEntity() {
    return newEntity;
  }

  public void setNewEntity(boolean newEntity) {
    this.newEntity = newEntity;
  }

  @PostLoad
  public void markNew() {
    this.newEntity = false;
  }


  public BigDecimal getRandomValue() {
    return randomValue;
  }

  public void setRandomValue(BigDecimal randomValue) {
    this.randomValue = randomValue;
  }
}