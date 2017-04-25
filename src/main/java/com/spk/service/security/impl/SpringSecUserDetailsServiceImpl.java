package com.spk.service.security.impl;

import com.spk.model.User;
import com.spk.service.UserService;
import com.spk.service.security.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by jt on 12/28/15.
 */
@Service("userDetailsService")
public class SpringSecUserDetailsServiceImpl implements UserDetailsService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private UserService userService;

  private Converter<User, UserDetails> userUserDetailsConverter;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDetailsImpl userDetails =
        (UserDetailsImpl) userUserDetailsConverter.convert(userService.findByUsername(username));
    return userDetails;
  }

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @Autowired
  @Qualifier(value = "userToUserDetails")
  public void setUserUserDetailsConverter(Converter<User, UserDetails> userUserDetailsConverter) {
    this.userUserDetailsConverter = userUserDetailsConverter;
  }
}
