package com.spk.model;

import com.spk.base.McnBaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gl552 on 4/25/2017.
 */
@Entity
public class Criteria extends McnBaseEntity {
  private static final long serialVersionUID = -8985277390452938447L;

  @Column
  private Double CFWeight;

  @Column
  private Double SFWeight;

  @Column
  private String name;

  @OneToMany(cascade = CascadeType.ALL,
      fetch = FetchType.EAGER,
      mappedBy = "criteria")
  private List<Subcriteria> subcriterias = new ArrayList<>();

  @Column
  private Double weight;

  public Double getCFWeight() {
    return CFWeight;
  }

  public void setCFWeight(Double CFWeight) {
    this.CFWeight = CFWeight;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getSFWeight() {
    return SFWeight;
  }

  public void setSFWeight(Double SFWeight) {
    this.SFWeight = SFWeight;
  }

  public List<Subcriteria> getSubcriterias() {
    return subcriterias;
  }

  public void setSubcriterias(List<Subcriteria> subcriterias) {
    this.subcriterias = subcriterias;
  }

  public Double getWeight() {
    return weight;
  }

  public void setWeight(Double weight) {
    this.weight = weight;
  }
}
