package com.spk.controller;

import com.spk.dto.register.RegisterDtoRequest;
import com.spk.model.Criteria;
import com.spk.model.User;
import com.spk.model.UserSubcriteria;
import com.spk.service.CriteriaService;
import com.spk.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mczal on 6/22/17.
 */
@Controller
@RequestMapping(RegisterController.ABSOLUTE_PATH)
public class RegisterController {
  public static final String ABSOLUTE_PATH = "/register";

  @Autowired
  private CriteriaService criteriaService;

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private UserService userService;

  @RequestMapping(method = RequestMethod.GET,
      value = {"", "/"})
  public String index(Model model, RegisterDtoRequest registerDtoRequest) {
    //    model.addAttribute("user", new RegisterDtoRequest());
    model.addAttribute("criterias", criteriaService.findAll());
    return "register";
  }

  @RequestMapping(method = RequestMethod.POST,
      value = "")
  public String register(Model model, @Valid RegisterDtoRequest registerDtoRequest,
      BindingResult bindingResult, RedirectAttributes redirectAttributes) throws Exception {
    if (bindingResult.hasErrors()) {
      logger.error(bindingResult.getAllErrors().toString());
      model.addAttribute("criterias", criteriaService.findAll());
      return "register";
    }

    logger.info("BirthDate: " + registerDtoRequest.getBirthDate());

    User user = new User();
    BeanUtils.copyProperties(registerDtoRequest, user);
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Date birthDate = df.parse(registerDtoRequest.getBirthDate());
    Calendar cal = Calendar.getInstance();
    cal.setTime(birthDate);
    LocalDate localDateNow = LocalDate
        .of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

    Calendar now = Calendar.getInstance();
    now.setTimeInMillis(System.currentTimeMillis());
    LocalDate localBirthDate = LocalDate
        .of(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
    Period period = Period.between(localDateNow, localBirthDate);

    user.setAge(period.getYears());

    registerDtoRequest.getSubcrits().forEach(s -> {
      String[] splitter = s.split("\\|");
      String critId = splitter[0];
      String subcritId = splitter[1];
      Integer value = Integer.parseInt(splitter[2]);

      Criteria criteria = criteriaService.findOne(critId);
      criteria.getSubcriterias().stream()
          .filter(subcriteria -> subcriteria.getId().equals(subcritId)).forEach(subcriteria -> {
        UserSubcriteria userSubcriteria = new UserSubcriteria();
        userSubcriteria.setScore(value);
        userSubcriteria.setUser(user);
        userSubcriteria.setSubcriteria(subcriteria);
        user.getUserSubcriterias().add(userSubcriteria);
      });

    });

    userService.save(user);

    redirectAttributes.addFlashAttribute("success",
        "Your Account has been created. Please login using your newly created username and password.");

    return "redirect:/login";
  }

}
