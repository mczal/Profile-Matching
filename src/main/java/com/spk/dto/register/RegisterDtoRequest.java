package com.spk.dto.register;

import com.spk.dto.constraint.StringOfNumbers;
import com.spk.model.utils.Gender;
import com.spk.model.utils.Religion;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mczal on 6/22/17.
 */
public class RegisterDtoRequest implements Serializable {
  private static final long serialVersionUID = -2083230589139609174L;

  @NotNull
  private String birthDate;

  @NotEmpty
  @NotNull
  private String email;

  @NotNull
  private Gender gender;

  @NotNull
  private Double height;

  @NotEmpty
  @NotNull
  private String name;

  @NotEmpty
  @NotNull
  private String password;

  @NotEmpty
  @NotNull
  @StringOfNumbers
  private String phone;

  @NotNull
  private Religion religion;

  private List<String> subcrits = new ArrayList<>();

  @NotEmpty
  @NotNull
  private String username;

  @NotNull
  @Min(0)
  private Double weight;

  public String getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
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

  public List<String> getSubcrits() {
    return subcrits;
  }

  public void setSubcrits(List<String> subcrits) {
    this.subcrits = subcrits;
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
    return "RegisterDtoRequest{" + "birthDate='" + birthDate + '\'' + ", email='" + email + '\''
        + ", gender=" + gender + ", height=" + height + ", name='" + name + '\'' + ", password='"
        + password + '\'' + ", phone='" + phone + '\'' + ", religion=" + religion + ", subcrits="
        + subcrits + ", username='" + username + '\'' + ", weight=" + weight + '}';
  }
}
