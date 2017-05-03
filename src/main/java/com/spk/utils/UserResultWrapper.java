package com.spk.utils;

import com.spk.model.User;

import java.io.Serializable;

/**
 * Created by Gl552 on 4/28/2017.
 */
public class UserResultWrapper implements Serializable {
  private static final long serialVersionUID = -3961240902984987301L;

  private double totalScore;

  private User user;

  public double getTotalScore() {
    return totalScore;
  }

  public void setTotalScore(double totalScore) {
    this.totalScore = totalScore;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "UserResultWrapper{" + "totalScore=" + totalScore + ", username=" + user.getUsername()
        + '}';
  }
}
