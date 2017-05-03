package com.spk.service;

import com.spk.model.User;
import com.spk.model.utils.Gender;

import java.util.List;

/**
 * Created by Gl552 on 4/25/2017.
 */
public interface UserService extends BaseService<User> {
  List<User> findByGender(Gender gender);

  List<User> findByGenderAndUsernameNot(Gender gender, String username);

  User findByUsername(String username);

  User update(User user);
}
