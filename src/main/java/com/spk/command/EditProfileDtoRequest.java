package com.spk.command;

import com.spk.model.UserSubcriteria;
import com.spk.model.utils.Gender;
import com.spk.model.utils.Religion;
import org.hibernate.validator.constraints.Email;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gl552 on 4/25/2017.
 */
public class EditProfileDtoRequest implements Serializable {
  private static final long serialVersionUID = 5908961302681645213L;

  private Integer age; // Av

  @Email
  private String email; // Av

  private Gender gender; // Av

  private Double height; // Av

  private String name; // Av

  private String phone;

  private Religion religion; // Av

  private List<UserSubcriteria> userSubcriterias = new ArrayList<>();

  private Double weight; // Av

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public List<UserSubcriteria> getUserSubcriterias() {
    return userSubcriterias;
  }

  public void setUserSubcriterias(List<UserSubcriteria> userSubcriterias) {
    this.userSubcriterias = userSubcriterias;
  }

  public Double getWeight() {
    return weight;
  }

  public void setWeight(Double weight) {
    this.weight = weight;
  }
}
