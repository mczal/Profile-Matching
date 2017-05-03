package com.spk.service.impl;

import com.spk.dao.UserDao;
import com.spk.model.User;
import com.spk.model.utils.Gender;
import com.spk.service.UserService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Gl552 on 4/25/2017.
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserDao userDao;

  @Override
  @Transactional(readOnly = false)
  public void delete(String id) {
    userDao.delete(id);
  }

  @Override
  public List<User> findAll() {
    return userDao.findAll();
  }

  @Override
  public List<User> findByGender(Gender gender) {
    return userDao.findByGender(gender);
  }

  @Override
  public List<User> findByGenderAndUsernameNot(Gender gender, String username) {
    return userDao.findByGenderAndUsernameIsNot(gender, username);
  }

  @Override
  public User findByUsername(String username) {
    return userDao.findByUsername(username);
  }

  @Override
  public User findOne(String id) {
    User user = userDao.findOne(id);
    Hibernate.initialize(user.getUserSubcriterias());
    return user;
  }

  @Override
  @Transactional(readOnly = false)
  public User save(User domain) {
    domain.setEncryptedPassword(passwordEncoder.encode(domain.getPassword()));
    return userDao.save(domain);
  }

  @Override
  @Transactional(readOnly = false)
  public User update(User user) {
    return userDao.save(user);
  }
}
