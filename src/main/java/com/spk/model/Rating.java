package com.spk.model;

import com.spk.base.McnBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Gl552 on 4/24/2017.
 */
@Entity
public class Rating extends McnBaseEntity {
  private static final long serialVersionUID = 6048687302202767310L;

  @Column
  private Integer rating;

  public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }
}
