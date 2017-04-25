package com.spk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

/**
 * Created by Gl552 on 4/25/2017.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  public void configAuhentication(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/resources/**").and().ignoring().antMatchers("/assets/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    //        http
    //            .authorizeRequests()
    //            .antMatchers("/signup","/about").permitAll()
    //            .anyRequest().hasRole("USER")
    //            .and()
    //            .formLogin()
    //            .loginPage("/login")
    //            .permitAll();
    http.headers().cacheControl().disable();

    http.headers().frameOptions().disable();
    http.csrf().ignoringAntMatchers("/h2-console").disable()

        .formLogin().loginPage("/login").usernameParameter("username").
        passwordParameter("password").permitAll().and()

        .logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout").
        invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll()

        .and().sessionManagement().sessionFixation().changeSessionId().maximumSessions(1)
        .maxSessionsPreventsLogin(true).sessionRegistry(getSessionRegistry()).and()

        .and().csrf().requireCsrfProtectionMatcher((r) -> {
      String m = r.getMethod();
      return !r.getServletPath().startsWith("/api/") && ("POST".equals(m) || "PUT".equals(m)
          || "DELETE".equals(m) || "PATCH".equals(m));
    })

        .and().authorizeRequests().antMatchers("/home/**").authenticated()
        //        .and().authorizeRequests().antMatchers("/api/product/**").permitAll()
        //        .and().authorizeRequests().antMatchers("/home").authenticated()
        //        .and().formLogin().loginPage("/login").permitAll()
        //        .and().authorizeRequests().antMatchers("/api/user/**").authenticated()
        //        .and().authorizeRequests().antMatchers("/api/user/**").permitAll()
        //        .and().authorizeRequests().antMatchers("/api/role/**").authenticated()
        //        .and().authorizeRequests().antMatchers("/api/user/**").hasAnyAuthority("ADMIN")
        .and().exceptionHandling().accessDeniedPage("/access_denied");
  }

  @Bean
  public SessionAuthenticationStrategy getSessionAuthStrategy(SessionRegistry sessionRegistry) {
    ConcurrentSessionControlAuthenticationStrategy controlAuthenticationStrategy =
        new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry);
    return controlAuthenticationStrategy;
  }

  @Bean
  public SessionRegistry getSessionRegistry() {
    return new SessionRegistryImpl();
  }

  @Bean(name = "passwordEncoder")
  public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
