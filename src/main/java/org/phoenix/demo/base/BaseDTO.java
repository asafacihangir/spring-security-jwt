package org.phoenix.demo.base;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class BaseDTO implements Serializable {

  private static final long serialVersionUID = 1L;


  private int version;


  private String createdBy;


  private LocalDateTime createdDate;


  private String lastModifiedBy;


  private LocalDateTime lastModifiedDate;


  private LocalDateTime deletedDate;

  private boolean deleted = false;


  private boolean newEntity = true;


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


}