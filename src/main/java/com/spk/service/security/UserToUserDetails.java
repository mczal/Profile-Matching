package com.spk.service.security;

import com.spk.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Gl552 on 11/19/2016.
 */
@Component
public class UserToUserDetails implements Converter<User, UserDetails> {

  @Override
  public UserDetails convert(User user) {
    UserDetailsImpl userDetails = new UserDetailsImpl();
    userDetails.setUsername(user.getUsername());
    userDetails.setPassword(user.getEncryptedPassword());
    userDetails.setEnabled(user.getEnabled());

    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

    user.getRoles().forEach(role -> {
      authorities.add(new SimpleGrantedAuthority(role.getRole()));
    });

    userDetails.setAuthorities(authorities);

    return userDetails;
  }
}
