package org.phoenix.demo.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.phoenix.demo.base.BaseEntity;


@Entity
@Table(name = "S_USER")
public class User extends BaseEntity {

  @Id
  @Column(name = "ID")
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  private String id;

  @JsonIgnore
  @Column(name = "PASSWORD")
  private String password;

  @Column(name = "FIRSTNAME")
  private String firstname;

  @Column(name = "LASTNAME")
  private String lastname;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "ACTIVATED")
  private boolean activated;

  @Enumerated(EnumType.STRING)
  private Role role;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }



  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isActivated() {
    return activated;
  }

  public void setActivated(boolean activated) {
    this.activated = activated;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}
