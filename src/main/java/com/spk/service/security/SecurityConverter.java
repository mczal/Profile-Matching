package com.spk.service.security;

import com.spk.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Gl552 on 11/29/2016.
 */
@Component
@Deprecated
public class SecurityConverter {

  public SecurityConverter() {
  }

  public static UserDetails convertUserToUserDetails(User user) {

    UserDetailsImpl userDetails = new UserDetailsImpl();
    userDetails.setUsername(user.getEmail());
    userDetails.setPassword(user.getEncryptedPassword());
    userDetails.setEnabled(user.getEnabled());
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();

    user.getRoles().forEach(role -> {
      authorities.add(new SimpleGrantedAuthority(role.getRole()));
    });

    userDetails.setAuthorities(authorities);

    return userDetails;
  }

  public static Logger getLogger() {
    return LoggerFactory.getLogger(SecurityConverter.class);
  }
}
