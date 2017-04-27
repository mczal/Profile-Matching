package com.spk.utils;

import com.spk.model.Criteria;
import com.spk.model.Subcriteria;
import com.spk.model.User;
import com.spk.model.UserSubcriteria;
import com.spk.model.utils.Factor;
import com.spk.model.utils.Gender;
import com.spk.model.utils.Religion;
import com.spk.service.CriteriaService;
import com.spk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Gl552 on 4/25/2017.
 */
@Component
public class JPABootstrap implements ApplicationListener<ContextRefreshedEvent> {

  private Subcriteria compliance = new Subcriteria();

  @Autowired
  private CriteriaService criteriaService;

  private Subcriteria dominance = new Subcriteria();

  private Subcriteria influence = new Subcriteria();

  private Subcriteria playMusic = new Subcriteria();

  private Subcriteria steadiness = new Subcriteria();

  @Autowired
  private UserService userService;

  private Subcriteria workout = new Subcriteria();

  private void loadCriteriaAndSubCriteria() {
    Criteria behaviour = new Criteria();
    behaviour.setWeight(0.5);
    behaviour.setName("Behaviour");
    behaviour.setCFWeight(0.6);
    behaviour.setSFWeight(0.4);

    //    Subcriteria dominance = new Subcriteria();
    dominance.setCriteria(behaviour);
    dominance.setFactor(Factor.CORE_FACTOR);
    dominance.setName("Dominance");
    behaviour.getSubcriterias().add(dominance);

    //    Subcriteria influence = new Subcriteria();
    influence.setCriteria(behaviour);
    influence.setFactor(Factor.CORE_FACTOR);
    influence.setName("Influence");
    behaviour.getSubcriterias().add(influence);

    //    Subcriteria steadiness = new Subcriteria();
    steadiness.setCriteria(behaviour);
    steadiness.setFactor(Factor.SECONDARY_FACTOR);
    steadiness.setName("Steadiness");
    behaviour.getSubcriterias().add(steadiness);

    //    Subcriteria compliance = new Subcriteria();
    compliance.setCriteria(behaviour);
    compliance.setFactor(Factor.SECONDARY_FACTOR);
    compliance.setName("Compliance");
    behaviour.getSubcriterias().add(compliance);

    criteriaService.save(behaviour);

    Criteria skill = new Criteria();
    skill.setSFWeight(0.4);
    skill.setCFWeight(0.6);
    skill.setName("Skill");
    skill.setWeight(0.5);

    playMusic.setCriteria(skill);
    playMusic.setName("Play Music");
    playMusic.setFactor(Factor.SECONDARY_FACTOR);
    skill.getSubcriterias().add(playMusic);

    workout.setCriteria(skill);
    workout.setName("Workout");
    workout.setFactor(Factor.CORE_FACTOR);
    skill.getSubcriterias().add(workout);

    criteriaService.save(skill);
  }

  private void loadUserAndContent() {
    User budi = new User();
    budi.setPassword("123");
    budi.setUsername("budi");
    budi.setAge(20);
    budi.setEmail("budi@budi.budi");
    budi.setGender(Gender.MAN);
    budi.setHeight(175.0);
    budi.setName("Budi");
    budi.setPhone("085600");
    budi.setReligion(Religion.MOESLIM);
    budi.setWeight(70.0);

    UserSubcriteria userDominance = new UserSubcriteria();
    userDominance.setScore(4);
    userDominance.setUser(budi);
    userDominance.setSubcriteria(dominance);
    budi.getUserSubcriterias().add(userDominance);

    UserSubcriteria userInfluence = new UserSubcriteria();
    userInfluence.setScore(4);
    userInfluence.setUser(budi);
    userInfluence.setSubcriteria(influence);
    budi.getUserSubcriterias().add(userInfluence);

    UserSubcriteria userSteadiness = new UserSubcriteria();
    userSteadiness.setScore(4);
    userSteadiness.setUser(budi);
    userSteadiness.setSubcriteria(steadiness);
    budi.getUserSubcriterias().add(userSteadiness);

    UserSubcriteria userCompliance = new UserSubcriteria();
    userCompliance.setScore(4);
    userCompliance.setUser(budi);
    userCompliance.setSubcriteria(compliance);
    budi.getUserSubcriterias().add(userCompliance);

    userService.save(budi);

    //----------------------------------------------------------//

    User anita = new User();
    anita.setUsername("anita");
    anita.setPassword("123");
    anita.setAge(24);
    anita.setEmail("anita@anita.anita");
    anita.setGender(Gender.WOMAN);
    anita.setHeight(175.0);
    anita.setName("Anita");
    anita.setPhone("085600");
    anita.setReligion(Religion.CATHOLIC);
    anita.setWeight(70.0);

    UserSubcriteria anitaDominance = new UserSubcriteria();
    anitaDominance.setScore(4);
    anitaDominance.setUser(anita);
    anitaDominance.setSubcriteria(dominance);
    anita.getUserSubcriterias().add(anitaDominance);

    UserSubcriteria anitaInfluence = new UserSubcriteria();
    anitaInfluence.setScore(3);
    anitaInfluence.setUser(anita);
    anitaInfluence.setSubcriteria(influence);
    anita.getUserSubcriterias().add(anitaInfluence);

    UserSubcriteria anitaSteadiness = new UserSubcriteria();
    anitaSteadiness.setScore(4);
    anitaSteadiness.setUser(anita);
    anitaSteadiness.setSubcriteria(steadiness);
    anita.getUserSubcriterias().add(anitaSteadiness);

    UserSubcriteria anitaCompliance = new UserSubcriteria();
    anitaCompliance.setScore(4);
    anitaCompliance.setUser(anita);
    anitaCompliance.setSubcriteria(compliance);
    anita.getUserSubcriterias().add(anitaCompliance);

    userService.save(anita);
	
	//----------------------------------------------------------//

    User ferdi = new User();
    anita.setUsername("ferdi");
    anita.setPassword("123");
    anita.setAge(27);
    anita.setEmail("ferdi@gmail.com");
    anita.setGender(Gender.MAN);
    anita.setHeight(165.0);
    anita.setName("Ferdi");
    anita.setPhone("085720202095");
    anita.setReligion(Religion.BUDDHA);
    anita.setWeight(66.0);

    UserSubcriteria anitaDominance = new UserSubcriteria();
    anitaDominance.setScore(2);
    anitaDominance.setUser(ferdi);
    anitaDominance.setSubcriteria(dominance);
    anita.getUserSubcriterias().add(anitaDominance);

    UserSubcriteria anitaInfluence = new UserSubcriteria();
    anitaInfluence.setScore(4);
    anitaInfluence.setUser(ferdi);
    anitaInfluence.setSubcriteria(influence);
    anita.getUserSubcriterias().add(anitaInfluence);

    UserSubcriteria anitaSteadiness = new UserSubcriteria();
    anitaSteadiness.setScore(3);
    anitaSteadiness.setUser(ferdi);
    anitaSteadiness.setSubcriteria(steadiness);
    anita.getUserSubcriterias().add(anitaSteadiness);

    UserSubcriteria anitaCompliance = new UserSubcriteria();
    anitaCompliance.setScore(5);
    anitaCompliance.setUser(ferdi);
    anitaCompliance.setSubcriteria(compliance);
    anita.getUserSubcriterias().add(anitaCompliance);

    userService.save(ferdi);
	
	//----------------------------------------------------------//

    User laila = new User();
    anita.setUsername("laila");
    anita.setPassword("123");
    anita.setAge(31);
    anita.setEmail("laila@gmail.com");
    anita.setGender(Gender.WOMAN);
    anita.setHeight(170.0);
    anita.setName("Laila");
    anita.setPhone("08787878933");
    anita.setReligion(Religion.MOESLIM);
    anita.setWeight(60.0);

    UserSubcriteria anitaDominance = new UserSubcriteria();
    anitaDominance.setScore(3);
    anitaDominance.setUser(laila);
    anitaDominance.setSubcriteria(dominance);
    anita.getUserSubcriterias().add(anitaDominance);

    UserSubcriteria anitaInfluence = new UserSubcriteria();
    anitaInfluence.setScore(4);
    anitaInfluence.setUser(laila);
    anitaInfluence.setSubcriteria(influence);
    anita.getUserSubcriterias().add(anitaInfluence);

    UserSubcriteria anitaSteadiness = new UserSubcriteria();
    anitaSteadiness.setScore(4);
    anitaSteadiness.setUser(laila);
    anitaSteadiness.setSubcriteria(steadiness);
    anita.getUserSubcriterias().add(anitaSteadiness);

    UserSubcriteria anitaCompliance = new UserSubcriteria();
    anitaCompliance.setScore(4);
    anitaCompliance.setUser(laila);
    anitaCompliance.setSubcriteria(compliance);
    anita.getUserSubcriterias().add(anitaCompliance);

    userService.save(laila);
	
	//----------------------------------------------------------//

    User qisti = new User();
    anita.setUsername("qisti");
    anita.setPassword("123");
    anita.setAge(35);
    anita.setEmail("qisti@gmail.com");
    anita.setGender(Gender.WOMAN);
    anita.setHeight(173.0);
    anita.setName("Qisti");
    anita.setPhone("08112233454");
    anita.setReligion(Religion.CHRISTIAN);
    anita.setWeight(70.0);

    UserSubcriteria anitaDominance = new UserSubcriteria();
    anitaDominance.setScore(5);
    anitaDominance.setUser(qisti);
    anitaDominance.setSubcriteria(dominance);
    anita.getUserSubcriterias().add(anitaDominance);

    UserSubcriteria anitaInfluence = new UserSubcriteria();
    anitaInfluence.setScore(3);
    anitaInfluence.setUser(qisti);
    anitaInfluence.setSubcriteria(influence);
    anita.getUserSubcriterias().add(anitaInfluence);

    UserSubcriteria anitaSteadiness = new UserSubcriteria();
    anitaSteadiness.setScore(1);
    anitaSteadiness.setUser(qisti);
    anitaSteadiness.setSubcriteria(steadiness);
    anita.getUserSubcriterias().add(anitaSteadiness);

    UserSubcriteria anitaCompliance = new UserSubcriteria();
    anitaCompliance.setScore(4);
    anitaCompliance.setUser(qisti);
    anitaCompliance.setSubcriteria(compliance);
    anita.getUserSubcriterias().add(anitaCompliance);

    userService.save(qisti);
	
	//----------------------------------------------------------//

    User erlangga = new User();
    anita.setUsername("erlangga");
    anita.setPassword("123");
    anita.setAge(37);
    anita.setEmail("erlangga@gmail.com");
    anita.setGender(Gender.MAN);
    anita.setHeight(169.0);
    anita.setName("Erlangga");
    anita.setPhone("0813469675");
    anita.setReligion(Religion.MOESLIM);
    anita.setWeight(73.0);

    UserSubcriteria anitaDominance = new UserSubcriteria();
    anitaDominance.setScore(2);
    anitaDominance.setUser(erlangga);
    anitaDominance.setSubcriteria(dominance);
    anita.getUserSubcriterias().add(anitaDominance);

    UserSubcriteria anitaInfluence = new UserSubcriteria();
    anitaInfluence.setScore(4);
    anitaInfluence.setUser(erlangga);
    anitaInfluence.setSubcriteria(influence);
    anita.getUserSubcriterias().add(anitaInfluence);

    UserSubcriteria anitaSteadiness = new UserSubcriteria();
    anitaSteadiness.setScore(5);
    anitaSteadiness.setUser(erlangga);
    anitaSteadiness.setSubcriteria(steadiness);
    anita.getUserSubcriterias().add(anitaSteadiness);

    UserSubcriteria anitaCompliance = new UserSubcriteria();
    anitaCompliance.setScore(3);
    anitaCompliance.setUser(erlangga);
    anitaCompliance.setSubcriteria(compliance);
    anita.getUserSubcriterias().add(anitaCompliance);

    userService.save(erlangga);
	
	//----------------------------------------------------------//

    User vava = new User();
    anita.setUsername("vava");
    anita.setPassword("123");
    anita.setAge(41);
    anita.setEmail("vava@gmail.com");
    anita.setGender(Gender.MAN);
    anita.setHeight(177.0);
    anita.setName("Vava");
    anita.setPhone("08873627547");
    anita.setReligion(Religion.CHRISTIAN);
    anita.setWeight(81.0);

    UserSubcriteria anitaDominance = new UserSubcriteria();
    anitaDominance.setScore(3);
    anitaDominance.setUser(vava);
    anitaDominance.setSubcriteria(dominance);
    anita.getUserSubcriterias().add(anitaDominance);

    UserSubcriteria anitaInfluence = new UserSubcriteria();
    anitaInfluence.setScore(3);
    anitaInfluence.setUser(vava);
    anitaInfluence.setSubcriteria(influence);
    anita.getUserSubcriterias().add(anitaInfluence);

    UserSubcriteria anitaSteadiness = new UserSubcriteria();
    anitaSteadiness.setScore(3);
    anitaSteadiness.setUser(vava);
    anitaSteadiness.setSubcriteria(steadiness);
    anita.getUserSubcriterias().add(anitaSteadiness);

    UserSubcriteria anitaCompliance = new UserSubcriteria();
    anitaCompliance.setScore(3);
    anitaCompliance.setUser(vava);
    anitaCompliance.setSubcriteria(compliance);
    anita.getUserSubcriterias().add(anitaCompliance);

    userService.save(vava);
	
	//----------------------------------------------------------//

    User gita = new User();
    anita.setUsername("gita");
    anita.setPassword("123");
    anita.setAge(41);
    anita.setEmail("gita@gmail.com");
    anita.setGender(Gender.WOMAN);
    anita.setHeight(169.0);
    anita.setName("Gita");
    anita.setPhone("0856782973");
    anita.setReligion(Religion.BUDDHA);
    anita.setWeight(68.0);

    UserSubcriteria anitaDominance = new UserSubcriteria();
    anitaDominance.setScore(4);
    anitaDominance.setUser(gita);
    anitaDominance.setSubcriteria(dominance);
    anita.getUserSubcriterias().add(anitaDominance);

    UserSubcriteria anitaInfluence = new UserSubcriteria();
    anitaInfluence.setScore(5);
    anitaInfluence.setUser(gita);
    anitaInfluence.setSubcriteria(influence);
    anita.getUserSubcriterias().add(anitaInfluence);

    UserSubcriteria anitaSteadiness = new UserSubcriteria();
    anitaSteadiness.setScore(3);
    anitaSteadiness.setUser(gita);
    anitaSteadiness.setSubcriteria(steadiness);
    anita.getUserSubcriterias().add(anitaSteadiness);

    UserSubcriteria anitaCompliance = new UserSubcriteria();
    anitaCompliance.setScore(1);
    anitaCompliance.setUser(gita);
    anitaCompliance.setSubcriteria(compliance);
    anita.getUserSubcriterias().add(anitaCompliance);

    userService.save(gita);
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    loadCriteriaAndSubCriteria();
    loadUserAndContent();
  }
}
