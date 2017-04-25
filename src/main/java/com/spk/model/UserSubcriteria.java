package com.spk.model;

import com.spk.base.McnBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * <p>
 * Created by Gl552 on 4/25/2017.
 * Mapping Many To Many Generated Table With Field Addition
 * </p>
 */

@Entity
public class UserSubcriteria extends McnBaseEntity {
  private static final long serialVersionUID = -8352045461428332649L;

  /**
   * On a scale for 1 - 5
   */
  @Column
  private Integer score;

  @ManyToOne
  @JoinColumn(name = "subcriteriaId")
  private Subcriteria subcriteria;

  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public Subcriteria getSubcriteria() {
    return subcriteria;
  }

  public void setSubcriteria(Subcriteria subcriteria) {
    this.subcriteria = subcriteria;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
