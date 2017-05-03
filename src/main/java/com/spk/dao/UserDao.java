package com.spk.dao;

import com.spk.model.User;
import com.spk.model.utils.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Gl552 on 4/24/2017.
 */
public interface UserDao extends JpaRepository<User, String> {

  List<User> findByAgeGreaterThanEqual(Integer age);

  List<User> findByAgeIsBetween(Integer lowerBound, Integer upperBound);

  List<User> findByAgeLessThanEqual(Integer age);

  User findByEmail(String email);

  List<User> findByGender(Gender gender);

  List<User> findByGenderAndUsernameIsNot(Gender gender, String username);

  User findByUsername(String username);
}
