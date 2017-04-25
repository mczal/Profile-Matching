package com.spk.command;

import com.spk.model.utils.Gender;
import com.spk.model.utils.Religion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gl552 on 4/25/2017.
 */
public class SearchDtoRequest implements Serializable {
  private static final long serialVersionUID = -7452480764674621482L;

  private String age;

  private Gender gender;

  private String height;

  private List<String> nkis = new ArrayList<>();

  private Religion religion;

  private String weight;

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public String getHeight() {
    return height;
  }

  public void setHeight(String height) {
    this.height = height;
  }

  public List<String> getNkis() {
    return nkis;
  }

  public void setNkis(List<String> nkis) {
    this.nkis = nkis;
  }

  public Religion getReligion() {
    return religion;
  }

  public void setReligion(Religion religion) {
    this.religion = religion;
  }

  public String getWeight() {
    return weight;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }

  public List<NKISubcriteria> parseNkisString() {
    List<NKISubcriteria> results = new ArrayList<>();
    this.nkis.forEach(s -> {
      NKISubcriteria nkiSubcriteria = new NKISubcriteria();
      String[] splitter = s.split(",");
      nkiSubcriteria.setCriteriaId(splitter[0]);
      nkiSubcriteria.setSubCriteriaId(splitter[1]);
      nkiSubcriteria.setIdeal(Integer.parseInt(splitter[2]));
      results.add(nkiSubcriteria);
    });
    return results;
  }

  @Override
  public String toString() {
    return "SearchDtoRequest{" + "age='" + age + '\'' + ", gender=" + gender + ", height='" + height
        + '\'' + ", nkis=" + nkis + ", religion=" + religion + ", weight='" + weight + '\'' + '}';
  }

}
