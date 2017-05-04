package com.spk.command;

/**
 * Created by Gl552 on 5/4/2017.
 */
public class BobotCritBSFBCF {

  private double BCF;

  private double BSF;

  private double bobotCriteria;

  private String criteriaId;

  public double getBCF() {
    return BCF;
  }

  public void setBCF(double BCF) {
    this.BCF = BCF;
  }

  public double getBSF() {
    return BSF;
  }

  public void setBSF(double BSF) {
    this.BSF = BSF;
  }

  public double getBobotCriteria() {
    return bobotCriteria;
  }

  public void setBobotCriteria(double bobotCriteria) {
    this.bobotCriteria = bobotCriteria;
  }

  public String getCriteriaId() {
    return criteriaId;
  }

  public void setCriteriaId(String criteriaId) {
    this.criteriaId = criteriaId;
  }

  @Override
  public String toString() {
    return "BobotCritBSFBCF{" + "BCF=" + BCF + ", BSF=" + BSF + ", bobotCriteria=" + bobotCriteria
        + ", criteriaId='" + criteriaId + '\'' + '}';
  }
}
