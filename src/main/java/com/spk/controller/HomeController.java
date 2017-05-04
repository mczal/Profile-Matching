package com.spk.controller;

import com.spk.command.BobotCritBSFBCF;
import com.spk.command.EditProfileDtoRequest;
import com.spk.command.NKISubcriteria;
import com.spk.command.SearchDtoRequest;
import com.spk.model.Criteria;
import com.spk.model.User;
import com.spk.model.utils.Factor;
import com.spk.model.utils.Gender;
import com.spk.model.utils.Religion;
import com.spk.service.CriteriaService;
import com.spk.service.UserService;
import com.spk.utils.UserResultWrapper;
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
import java.util.Collections;
import java.util.List;

/**
 * Created by Gl552 on 4/25/2017.
 */
@Controller
@RequestMapping(HomeController.ABSOLUTE_PATH)
public class HomeController {
  public static final String ABSOLUTE_PATH = "/home";

  public static final String LAYOUTS = "layouts/main";

  private static final int MAX_SCALE = 5;

  @Autowired
  private CriteriaService criteriaService;

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private UserService userService;

  private List<UserResultWrapper> calculateProfileMatching(List<NKISubcriteria> nkiSubcriterias,
      List<User> users, List<BobotCritBSFBCF> bobotsz) {
    List<UserResultWrapper> results = new ArrayList<>();

    users.forEach(user -> {
      //      logger.info("calculation for user: " + user.getId());
      //      System.err.println("Alternative (user): #" + user.getUsername());
      List<Criteria> criterias = criteriaService.findAll();
      List<Double> allCriteriaResult = new ArrayList<>();
      criterias.forEach(crit -> {
        /**
         * Calculate Gap For Each User Input For Each SubCrit In CurrentCriteria
         * */
        //-------------
        List<Double> coreFactorGap = new ArrayList<>();
        List<Double> secondaryFactorGap = new ArrayList<>();
        user.getUserSubcriterias().stream().filter(
            userSubcriteria -> userSubcriteria.getSubcriteria().getCriteria().getId()
                .equals(crit.getId())).forEach(userSubcriteria -> {
          //          logger.info(
          //              "crit->" + crit.getName() + ", sub->" + userSubcriteria.getSubcriteria().getName());
          nkiSubcriterias.stream().filter(nkiSubcriteria -> nkiSubcriteria.getSubCriteriaId()
              .equals(userSubcriteria.getSubcriteria().getId())).forEach(nkiSubcriteria -> {
            int gap = nkiSubcriteria.getIdeal() - userSubcriteria.getScore();
            double gapScore = retreiveGapScore(gap, MAX_SCALE);
            if (userSubcriteria.getSubcriteria().getFactor() == Factor.CORE_FACTOR) {
              coreFactorGap.add(gapScore);
            } else if (userSubcriteria.getSubcriteria().getFactor() == Factor.SECONDARY_FACTOR) {
              secondaryFactorGap.add(gapScore);
            }
          });
        });
        //        System.err.println("For Crit: " + crit.getName() + "-> Core: " + coreFactorGap.toString());
        //        System.err.println(
        //            "For Crit: " + crit.getName() + "-> Secondary: " + secondaryFactorGap.toString());
        int cfCount = 0;
        double cfSum = 0;
        for (Double c : coreFactorGap) {
          cfCount++;
          cfSum += c;
        }
        double NCF = cfSum / cfCount * 1.0;
        int sfCount = 0;
        double sfSum = 0;
        for (Double s : secondaryFactorGap) {
          sfCount++;
          sfSum += s;
        }
        double NSF = sfSum / sfCount * 1.0;

        bobotsz.stream()
            .filter(bobotCritBSFBCF -> bobotCritBSFBCF.getCriteriaId().equals(crit.getId()))
            .forEach(bobotCritBSFBCF -> {
              double totalCriteriaScore =
                  (bobotCritBSFBCF.getBCF() * NCF) + (bobotCritBSFBCF.getBSF() * NSF);
              //        logger.info("totalCriteriaScore: " + totalCriteriaScore);
              double critScoreTimesCritWeight =
                  totalCriteriaScore * bobotCritBSFBCF.getBobotCriteria();
              allCriteriaResult.add(critScoreTimesCritWeight);
            });

      });

      //      logger.info("\nAllCriteriaResult: " + allCriteriaResult.toString());
      double sumAllCriteriaResult = 0;
      for (Double aDouble : allCriteriaResult) {
        sumAllCriteriaResult += aDouble;
      }
      UserResultWrapper currAltResult = new UserResultWrapper();
      currAltResult.setUser(user);
      currAltResult.setTotalScore(sumAllCriteriaResult);
      results.add(currAltResult);
    });



    //    System.out.println("\nBefore: ");
    //    for (int i = 0; i < nkiSubcriterias.size(); i++) {
    //      System.out.println(nkiSubcriterias.get(i).getCriteriaId());
    //    }
    //    nkiSubcriterias.sort(Comparator.comparing(NKISubcriteria::getCriteriaId));
    //    System.out.println("\nAfter: ");
    //    for (int i = 0; i < nkiSubcriterias.size(); i++) {
    //      System.out.println(nkiSubcriterias.get(i).getCriteriaId());
    //    }
    //    logger.info("After: " + nkiSubcriterias.toString());

    Collections.sort(results, (o1, o2) -> {
      if (o1.getTotalScore() > o2.getTotalScore()) {
        return -1;
      } else if (o1.getTotalScore() < o2.getTotalScore()) {
        return 1;
      } else {
        return 0;
      }
    });
    return results.subList(0, 5);
  }

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
  public String postSearchMatching(Model model, SearchDtoRequest object) throws Exception {
    //    logger.info("\n" + object.toString() + "\n");
    UserDetails userDetails = (UserDetails) getAuth().getPrincipal();
    List<User> users =
        userService.findByGenderAndUsernameNot(object.getGender(), userDetails.getUsername());
    if (users.isEmpty()) {
      model.addAttribute("results", new ArrayList<>());
      model.addAttribute("auth", getAuth());
      model.addAttribute("count", 0);
      model.addAttribute("view", "home/result");
      return LAYOUTS;
    }
    /**
     * Check For Age
     * */
    List<User> currentResults = new ArrayList<>();
    if (!object.getAge().equalsIgnoreCase("ignore")) {
      logger.info("Not equals ignore for age");
      //      currentResults = ;
      users.stream().filter(user -> {
        if (object.getAge().contains("<")) {
          int lessAge = Integer.parseInt(object.getAge().split(" ")[1]);
          if (user.getAge().compareTo(lessAge) >= 0) {
            return false;
          }
          return true;
        } else if (object.getAge().contains(">")) {
          int greaterAge = Integer.parseInt(object.getAge().split(" ")[1]);
          if (user.getAge().compareTo(greaterAge) <= 0) {
            return false;
          }
          return true;
        } else {
          String[] splitter = object.getAge().split("-");
          //          logger.info("Splitter: " + Arrays.toString(splitter));
          int lowerBound = Integer.parseInt(splitter[0]);
          int upperBound = Integer.parseInt(splitter[1]);
          if (user.getAge().compareTo(lowerBound) >= 0
              && user.getAge().compareTo(upperBound) <= 0) {
            return true;
          }
          return false;
        }
      }).forEach(currentResults::add);
      //      logger.info("Result age: " + currentResults.toString());
    }
    if (!object.getAge().equalsIgnoreCase("ignore")) {
      if (!currentResults.isEmpty()) {
        users = currentResults;
      } else {
        users.clear();
      }
    }
    if (users.isEmpty()) {
      model.addAttribute("results", new ArrayList<>());
      model.addAttribute("auth", getAuth());
      model.addAttribute("count", 0);
      model.addAttribute("view", "home/result");
      return LAYOUTS;
    }
    //----------------------------------------------

    /**
     * Check for weight
     * */
    currentResults = new ArrayList<>();
    if (!object.getWeight().equalsIgnoreCase("ignore")) {
      //      logger.info("Not equals ignore for weight");
      //      currentResults = ;
      users.stream().filter(user -> {
        if (object.getWeight().contains("<")) {
          double lessWeight = Integer.parseInt(object.getWeight().split(" ")[1]) * 1.0;
          if (user.getWeight().compareTo(lessWeight) >= 0) {
            return false;
          }
          return true;
        } else if (object.getWeight().contains(">")) {
          double greaterWeight = Integer.parseInt(object.getWeight().split(" ")[1]) * 1.0;
          if (user.getWeight().compareTo(greaterWeight) <= 0) {
            return false;
          }
          return true;
        } else {
          String[] splitter = object.getWeight().split("-");
          //          logger.info("Splitter: " + Arrays.toString(splitter));
          double lowerBound = Integer.parseInt(splitter[0]) * 1.0;
          double upperBound = Integer.parseInt(splitter[1]) * 1.0;
          if (user.getWeight().compareTo(lowerBound) >= 0
              && user.getWeight().compareTo(upperBound) <= 0) {
            return true;
          }
          return false;
        }
      }).forEach(currentResults::add);
      //      logger.info("Result age: " + currentResults.toString());
    }
    if (!object.getWeight().equalsIgnoreCase("ignore")) {
      if (!currentResults.isEmpty()) {
        users = currentResults;
      } else {
        users.clear();
      }
    }
    if (users.isEmpty()) {
      model.addAttribute("results", new ArrayList<>());
      model.addAttribute("auth", getAuth());
      model.addAttribute("count", 0);
      model.addAttribute("view", "home/result");
      return LAYOUTS;
    }
    //----------------------------------------------

    /**
     * Check for height
     * */
    currentResults = new ArrayList<>();
    if (!object.getHeight().equalsIgnoreCase("ignore")) {
      //      logger.info("Not equals ignore for height");
      //      currentResults = ;
      users.stream().filter(user -> {
        if (object.getHeight().contains("<")) {
          double lessHeight = Integer.parseInt(object.getHeight().split(" ")[1]) * 1.0;
          if (user.getHeight().compareTo(lessHeight) >= 0) {
            return false;
          }
          return true;
        } else if (object.getHeight().contains(">")) {
          double greaterHeight = Integer.parseInt(object.getHeight().split(" ")[1]) * 1.0;
          if (user.getHeight().compareTo(greaterHeight) <= 0) {
            return false;
          }
          return true;
        } else {
          String[] splitter = object.getHeight().split("-");
          //          logger.info("Splitter: " + Arrays.toString(splitter));
          double lowerBound = Integer.parseInt(splitter[0]) * 1.0;
          double upperBound = Integer.parseInt(splitter[1]) * 1.0;
          if (user.getHeight().compareTo(lowerBound) >= 0
              && user.getHeight().compareTo(upperBound) <= 0) {
            return true;
          }
          return false;
        }
      }).forEach(currentResults::add);
      //      logger.info("Result age: " + currentResults.toString());
    }
    if (!object.getHeight().equalsIgnoreCase("ignore")) {
      if (!currentResults.isEmpty()) {
        users = currentResults;
      } else {
        users.clear();
      }
    }
    if (users.isEmpty()) {
      model.addAttribute("results", new ArrayList<>());
      model.addAttribute("auth", getAuth());
      model.addAttribute("count", 0);
      model.addAttribute("view", "home/result");
      return LAYOUTS;
    }

    /**
     * Check for Religion
     * */
    currentResults = new ArrayList<>();
    if (object.getReligion() != Religion.IGNORE) {
      //      logger.info("Not equals ignore for religion");
      users.stream().filter(user -> user.getReligion() == object.getReligion())
          .forEach(currentResults::add);
    }
    if (object.getReligion() != Religion.IGNORE) {
      if (!currentResults.isEmpty()) {
        users = currentResults;
      } else {
        users.clear();
      }
    }
    if (users.isEmpty()) {
      model.addAttribute("results", new ArrayList<>());
      model.addAttribute("auth", getAuth());
      model.addAttribute("count", 0);
      model.addAttribute("view", "home/result");
      return LAYOUTS;
    }


    //    logger.info("Users: " + users.toString());

    //    if (1 == 1) {
    //      throw new Exception(
    //          "\n" + object.getBobotBcf().toString() + "\n" + object.getBobotCrit() + "\n");
    //    }


    logger.info("\n" + object.parseBobotsz() + "\n");

    List<UserResultWrapper> results =
        this.calculateProfileMatching(object.parseNkisString(), users, object.parseBobotsz());
    //    logger.info("\n\nResult In Controller: " + results.toString());

    model.addAttribute("results", results);
    model.addAttribute("auth", getAuth());
    model.addAttribute("count", results.size());
    model.addAttribute("view", "home/result");
    return LAYOUTS;
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

  private double retreiveGapScore(int gap, int maxScale) {
    /**
     * Untuk skala 1 - 5
     * */
    if (maxScale == 5) {
      switch (gap) {
        case 0:
          return 5;
        case 1:
          return 4.5;
        case -1:
          return 4;
        case 2:
          return 3.5;
        case -2:
          return 3;
        case 3:
          return 2.5;
        case -3:
          return 2;
        case 4:
          return 1.5;
        case -4:
          return 1;
        default:
          throw new RuntimeException("Gap value is not acceptable");
      }
    } else if (maxScale == 10) {
      throw new IllegalArgumentException("Not Yet Implemented");
    } else {
      throw new RuntimeException("Max Scale may only be filled with 5 or 10");
    }

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
