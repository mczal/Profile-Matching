package com.spk.controller;

import com.spk.command.EditProfileDtoRequest;
import com.spk.command.SearchDtoRequest;
import com.spk.model.User;
import com.spk.model.utils.Gender;
import com.spk.model.utils.Religion;
import com.spk.service.CriteriaService;
import com.spk.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gl552 on 4/25/2017.
 */
@Controller
@RequestMapping(HomeController.ABSOLUTE_PATH)
public class HomeController {
  public static final String ABSOLUTE_PATH = "/home";

  public static final String LAYOUTS = "layouts/main";

  @Autowired
  private CriteriaService criteriaService;

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private UserService userService;

  private Authentication getAuth() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  @RequestMapping("")
  public String home(Model model) {
    model.addAttribute("auth", getAuth());
    model.addAttribute("view", "home/index");
    return LAYOUTS;
  }

  @RequestMapping(method = RequestMethod.POST,
      value = "/profile-matching")
  public String postSearchMatching(Model model, SearchDtoRequest object) {
    //    logger.info("\n" + object.toString() + "\n");

    List<User> users = userService.findAll();
    List<User> resultAge = new ArrayList<>();
    if (!object.getAge().equalsIgnoreCase("ignore")) {
      users.stream().filter(user -> {
        if (object.getAge().contains("<")) {
          int lessAge = Integer.parseInt(object.getAge().split(" ")[1]);
          if (user.getAge().compareTo(lessAge) >= 0) {
            return true;
          }
          return false;
        } else if (object.getAge().contains(">")) {
          int greaterAge = Integer.parseInt(object.getAge().split(" ")[1]);
          if (user.getAge().compareTo(greaterAge) <= 0) {
            return true;
          }
          return false;
        } else {
          String[] splitter = object.getAge().split("-");
          int lowerBound = Integer.parseInt(splitter[0]);
          int upperBound = Integer.parseInt(splitter[1]);
          if (user.getAge().compareTo(lowerBound) >= 0
              && user.getAge().compareTo(upperBound) <= 0) {
            return true;
          }
          return false;
        }
      }).forEach(user -> {
        resultAge.add(user);
      });

    }

    return "redirect:/home/search";
  }

  @RequestMapping("/profile")
  public String profile(Model model) {
    model.addAttribute("view", "home/profile");
    model.addAttribute("auth", getAuth());

    UserDetails userDetails = (UserDetails) getAuth().getPrincipal();

    User user = userService.findByUsername(userDetails.getUsername());
    if (user == null) {
      throw new AuthenticationServiceException("User not found");
    }

    EditProfileDtoRequest dto = new EditProfileDtoRequest();
    BeanUtils.copyProperties(user, dto);

    model.addAttribute("object", dto);
    model.addAttribute("genders", Gender.values());
    model.addAttribute("religions", Religion.values());
    return LAYOUTS;
  }

  @RequestMapping("/search")
  public String search(Model model) {
    model.addAttribute("view", "home/search");
    model.addAttribute("auth", getAuth());

    model.addAttribute("genders", Gender.values());
    model.addAttribute("religions", Religion.values());
    model.addAttribute("criterias", criteriaService.findAll());

    model.addAttribute("object", new SearchDtoRequest());
    return LAYOUTS;
  }

}
