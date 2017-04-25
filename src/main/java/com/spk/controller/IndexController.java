package com.spk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Gl552 on 4/25/2017.
 */
@Controller
public class IndexController {

  @RequestMapping({"", "/"})
  public String index() {
    return "redirect:" + HomeController.ABSOLUTE_PATH;
  }

  @RequestMapping("login")
  public String loginForm() {
    return "login";
  }
}
