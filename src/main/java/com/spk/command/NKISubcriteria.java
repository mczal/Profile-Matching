package com.spk.command;

import java.io.Serializable;

/**
 * Created by Gl552 on 4/25/2017.
 */
public class NKISubcriteria implements Serializable {
  private static final long serialVersionUID = 605324039424073727L;

  private String criteriaId;

  private Integer ideal;

  private String subCriteriaId;

  public String getCriteriaId() {
    return criteriaId;
  }

  public void setCriteriaId(String criteriaId) {
    this.criteriaId = criteriaId;
  }

  public Integer getIdeal() {
    return ideal;
  }

  public void setIdeal(Integer ideal) {
    this.ideal = ideal;
  }

  public String getSubCriteriaId() {
    return subCriteriaId;
  }

  public void setSubCriteriaId(String subCriteriaId) {
    this.subCriteriaId = subCriteriaId;
  }

  @Override
  public String toString() {
    return "NKISubcriteria{" + "criteriaId='" + criteriaId + '\'' + ", ideal=" + ideal
        + ", subCriteriaId='" + subCriteriaId + '\'' + '}';
  }
}
