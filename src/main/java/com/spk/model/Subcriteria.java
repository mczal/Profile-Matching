package com.spk.model;

import com.spk.base.McnBaseEntity;
import com.spk.model.utils.Factor;

import javax.persistence.*;

/**
 * Created by Gl552 on 4/25/2017.
 */
@Entity
public class Subcriteria extends McnBaseEntity {
  private static final long serialVersionUID = 4400932649941047986L;

  @ManyToOne
  @JoinColumn(name = "criteriaId")
  private Criteria criteria;

  @Column
  @Enumerated(EnumType.STRING)
  private Factor factor;

  @Column
  private String name;

  public Criteria getCriteria() {
    return criteria;
  }

  public void setCriteria(Criteria criteria) {
    this.criteria = criteria;
  }

  public Factor getFactor() {
    return factor;
  }

  public void setFactor(Factor factor) {
    this.factor = factor;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
