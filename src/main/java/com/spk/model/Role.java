package com.spk.model;

import com.spk.base.McnBaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Gl552 on 4/25/2017.
 */
@Entity
public class Role extends McnBaseEntity {
  public static final String TABLE_NAME = "role";

  private static final String ROLE = "role";

  private static final long serialVersionUID = -7806959724372050341L;

  @Column(name = ROLE)
  private String role;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "USER_ROLE",
      joinColumns = @JoinColumn(name = "role_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> users = new ArrayList<>();

  public void addUser(User user) {
    if (!this.users.contains(user)) {
      this.users.add(user);
    }

    if (!user.getRoles().contains(this)) {
      user.getRoles().add(this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    Role role1 = (Role) o;
    return Objects.equals(role, role1.role);
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), role);
  }

  @Override
  public String toString() {
    return "Role{" + "role='" + role + '\'' + ", users=" + users + '}';
  }
}
