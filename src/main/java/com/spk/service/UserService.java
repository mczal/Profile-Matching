package com.spk.service;

import com.spk.model.User;

/**
 * Created by Gl552 on 4/25/2017.
 */
public interface UserService extends BaseService<User> {
  User findByUsername(String username);

  User update(User user);
}
