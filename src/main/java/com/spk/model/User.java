package com.spk.model;

import com.spk.base.McnBaseEntity;
import com.spk.model.utils.Gender;
import com.spk.model.utils.Religion;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gl552 on 4/24/2017.
 */
@Entity
@Table(name = "user",
    uniqueConstraints = {@UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")})
public class User extends McnBaseEntity {
  private static final long serialVersionUID = -4067980028045491570L;

  @Column
  private Integer age;

  @Column
  private String email;

  private Boolean enabled = true;

  @Column
  private String encryptedPassword;

  /**
   * Initial Criteria
   */
  @Column
  private Gender gender;

  @Column
  private Double height;

  /**
   * Personal Information
   */
  @Column
  private String name;

  @Transient
  private String password;

  @Column
  private String phone;

  @Column
  @Enumerated(EnumType.STRING)
  private Religion religion;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "USER_ROLE",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private List<Role> roles = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL,
      fetch = FetchType.LAZY,
      mappedBy = "user")
  private List<UserSubcriteria> userSubcriterias = new ArrayList<>();

  @Column(name = "username")
  private String username;

  @Column
  private Double weight;

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public String getEncryptedPassword() {
    return encryptedPassword;
  }

  public void setEncryptedPassword(String encryptedPassword) {
    this.encryptedPassword = encryptedPassword;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public Double getHeight() {
    return height;
  }

  public void setHeight(Double height) {
    this.height = height;
  }

  /**
   * Criteria
   */

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Religion getReligion() {
    return religion;
  }

  public void setReligion(Religion religion) {
    this.religion = religion;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  public List<UserSubcriteria> getUserSubcriterias() {
    return userSubcriterias;
  }

  public void setUserSubcriterias(List<UserSubcriteria> userSubcriterias) {
    this.userSubcriterias = userSubcriterias;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Double getWeight() {
    return weight;
  }

  public void setWeight(Double weight) {
    this.weight = weight;
  }

  @Override
  public String toString() {
    return "User{" + "age=" + age + ", email='" + email + '\'' + ", enabled=" + enabled
        + ", encryptedPassword='" + encryptedPassword + '\'' + ", gender=" + gender + ", height="
        + height + ", name='" + name + '\'' + ", password='" + password + '\'' + ", phone='" + phone
        + '\'' + ", religion=" + religion + ", roles=" + roles + ", userSubcriterias="
        + userSubcriterias + ", username='" + username + '\'' + ", weight=" + weight + '}';
  }

}
