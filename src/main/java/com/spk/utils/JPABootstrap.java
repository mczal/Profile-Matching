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

    UserSubcriteria userPlaymusic = new UserSubcriteria();
    userPlaymusic.setScore(4);
    userPlaymusic.setUser(budi);
    userPlaymusic.setSubcriteria(playMusic);
    budi.getUserSubcriterias().add(userPlaymusic);

    UserSubcriteria userWorkout = new UserSubcriteria();
    userWorkout.setScore(4);
    userWorkout.setUser(budi);
    userWorkout.setSubcriteria(workout);
    budi.getUserSubcriterias().add(userWorkout);

    //    UserSubcriteria userPlaymusic = new UserSubcriteria();
    //    userPlaymusic.setScore(4);
    //    userPlaymusic.setUser(budi);
    //    userPlaymusic.setSubcriteria(playMusic);
    //    budi.getUserSubcriterias().add(userPlaymusic);
    //
    //    UserSubcriteria userWorkout = new UserSubcriteria();
    //    userWorkout.setScore(4);
    //    userWorkout.setUser(budi);
    //    userWorkout.setSubcriteria(workout);
    //    budi.getUserSubcriterias().add(userWorkout);

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

    UserSubcriteria anitaPlaymusic = new UserSubcriteria();
    anitaPlaymusic.setScore(4);
    anitaPlaymusic.setUser(anita);
    anitaPlaymusic.setSubcriteria(playMusic);
    anita.getUserSubcriterias().add(anitaPlaymusic);

    UserSubcriteria anitaWorkout = new UserSubcriteria();
    anitaWorkout.setScore(4);
    anitaWorkout.setUser(anita);
    anitaWorkout.setSubcriteria(workout);
    anita.getUserSubcriterias().add(anitaWorkout);

    //    UserSubcriteria anitaPlaymusic = new UserSubcriteria();
    //    anitaPlaymusic.setScore(4);
    //    anitaPlaymusic.setUser(anita);
    //    anitaPlaymusic.setSubcriteria(playMusic);
    //    anita.getUserSubcriterias().add(anitaPlaymusic);
    //
    //    UserSubcriteria anitaWorkout = new UserSubcriteria();
    //    anitaWorkout.setScore(4);
    //    anitaWorkout.setUser(anita);
    //    anitaWorkout.setSubcriteria(workout);
    //    anita.getUserSubcriterias().add(anitaWorkout);


    //----------------------------------------------------------//

    User ferdi = new User();
    ferdi.setUsername("ferdi");
    ferdi.setPassword("123");
    ferdi.setAge(27);
    ferdi.setEmail("ferdi@gmail.com");
    ferdi.setGender(Gender.MAN);
    ferdi.setHeight(165.0);
    ferdi.setName("Ferdi");
    ferdi.setPhone("085720202095");
    ferdi.setReligion(Religion.BUDDHA);
    ferdi.setWeight(66.0);

    UserSubcriteria ferdiDominance = new UserSubcriteria();
    ferdiDominance.setScore(2);
    ferdiDominance.setUser(ferdi);
    ferdiDominance.setSubcriteria(dominance);
    ferdi.getUserSubcriterias().add(ferdiDominance);

    UserSubcriteria ferdiInfluence = new UserSubcriteria();
    ferdiInfluence.setScore(4);
    ferdiInfluence.setUser(ferdi);
    ferdiInfluence.setSubcriteria(influence);
    ferdi.getUserSubcriterias().add(ferdiInfluence);

    UserSubcriteria ferdiSteadiness = new UserSubcriteria();
    ferdiSteadiness.setScore(3);
    ferdiSteadiness.setUser(ferdi);
    ferdiSteadiness.setSubcriteria(steadiness);
    ferdi.getUserSubcriterias().add(ferdiSteadiness);

    UserSubcriteria ferdiCompliance = new UserSubcriteria();
    ferdiCompliance.setScore(5);
    ferdiCompliance.setUser(ferdi);
    ferdiCompliance.setSubcriteria(compliance);
    ferdi.getUserSubcriterias().add(ferdiCompliance);

    UserSubcriteria ferdiPlaymusic = new UserSubcriteria();
    ferdiPlaymusic.setScore(5);
    ferdiPlaymusic.setUser(ferdi);
    ferdiPlaymusic.setSubcriteria(playMusic);
    ferdi.getUserSubcriterias().add(ferdiPlaymusic);

    UserSubcriteria ferdiWorkout = new UserSubcriteria();
    ferdiWorkout.setScore(3);
    ferdiWorkout.setUser(ferdi);
    ferdiWorkout.setSubcriteria(workout);
    ferdi.getUserSubcriterias().add(ferdiWorkout);

    userService.save(ferdi);

    //----------------------------------------------------------//

    User laila = new User();
    laila.setUsername("laila");
    laila.setPassword("123");
    laila.setAge(31);
    laila.setEmail("laila@gmail.com");
    laila.setGender(Gender.WOMAN);
    laila.setHeight(170.0);
    laila.setName("Laila");
    laila.setPhone("08787878933");
    laila.setReligion(Religion.MOESLIM);
    laila.setWeight(60.0);

    UserSubcriteria lailaDominance = new UserSubcriteria();
    lailaDominance.setScore(3);
    lailaDominance.setUser(laila);
    lailaDominance.setSubcriteria(dominance);
    laila.getUserSubcriterias().add(lailaDominance);

    UserSubcriteria lailaInfluence = new UserSubcriteria();
    lailaInfluence.setScore(4);
    lailaInfluence.setUser(laila);
    lailaInfluence.setSubcriteria(influence);
    laila.getUserSubcriterias().add(lailaInfluence);

    UserSubcriteria lailaSteadiness = new UserSubcriteria();
    lailaSteadiness.setScore(4);
    lailaSteadiness.setUser(laila);
    lailaSteadiness.setSubcriteria(steadiness);
    laila.getUserSubcriterias().add(lailaSteadiness);

    UserSubcriteria lailaCompliance = new UserSubcriteria();
    lailaCompliance.setScore(4);
    lailaCompliance.setUser(laila);
    lailaCompliance.setSubcriteria(compliance);
    laila.getUserSubcriterias().add(lailaCompliance);

    UserSubcriteria lailaPlaymusic = new UserSubcriteria();
    lailaPlaymusic.setScore(2);
    lailaPlaymusic.setUser(laila);
    lailaPlaymusic.setSubcriteria(playMusic);
    laila.getUserSubcriterias().add(lailaPlaymusic);

    UserSubcriteria lailaWorkout = new UserSubcriteria();
    lailaWorkout.setScore(5);
    lailaWorkout.setUser(laila);
    lailaWorkout.setSubcriteria(workout);
    laila.getUserSubcriterias().add(lailaWorkout);

    userService.save(laila);

    //----------------------------------------------------------//

    User qisti = new User();
    qisti.setUsername("qisti");
    qisti.setPassword("123");
    qisti.setAge(35);
    qisti.setEmail("qisti@gmail.com");
    qisti.setGender(Gender.WOMAN);
    qisti.setHeight(173.0);
    qisti.setName("Qisti");
    qisti.setPhone("08112233454");
    qisti.setReligion(Religion.CHRISTIAN);
    qisti.setWeight(70.0);

    UserSubcriteria qistiDominance = new UserSubcriteria();
    qistiDominance.setScore(5);
    qistiDominance.setUser(qisti);
    qistiDominance.setSubcriteria(dominance);
    qisti.getUserSubcriterias().add(qistiDominance);

    UserSubcriteria qistiInfluence = new UserSubcriteria();
    qistiInfluence.setScore(3);
    qistiInfluence.setUser(qisti);
    qistiInfluence.setSubcriteria(influence);
    qisti.getUserSubcriterias().add(qistiInfluence);

    UserSubcriteria qistiSteadiness = new UserSubcriteria();
    qistiSteadiness.setScore(1);
    qistiSteadiness.setUser(qisti);
    qistiSteadiness.setSubcriteria(steadiness);
    qisti.getUserSubcriterias().add(qistiSteadiness);

    UserSubcriteria qistiCompliance = new UserSubcriteria();
    qistiCompliance.setScore(4);
    qistiCompliance.setUser(qisti);
    qistiCompliance.setSubcriteria(compliance);
    qisti.getUserSubcriterias().add(qistiCompliance);

    UserSubcriteria qistiPlaymusic = new UserSubcriteria();
    qistiPlaymusic.setScore(1);
    qistiPlaymusic.setUser(qisti);
    qistiPlaymusic.setSubcriteria(playMusic);
    qisti.getUserSubcriterias().add(qistiPlaymusic);

    UserSubcriteria qistiWorkout = new UserSubcriteria();
    qistiWorkout.setScore(4);
    qistiWorkout.setUser(qisti);
    qistiWorkout.setSubcriteria(workout);
    qisti.getUserSubcriterias().add(qistiWorkout);

    userService.save(qisti);

    //----------------------------------------------------------//

    User erlangga = new User();
    erlangga.setUsername("erlangga");
    erlangga.setPassword("123");
    erlangga.setAge(37);
    erlangga.setEmail("erlangga@gmail.com");
    erlangga.setGender(Gender.MAN);
    erlangga.setHeight(169.0);
    erlangga.setName("Erlangga");
    erlangga.setPhone("0813469675");
    erlangga.setReligion(Religion.MOESLIM);
    erlangga.setWeight(73.0);

    UserSubcriteria erlanggaDominance = new UserSubcriteria();
    erlanggaDominance.setScore(2);
    erlanggaDominance.setUser(erlangga);
    erlanggaDominance.setSubcriteria(dominance);
    erlangga.getUserSubcriterias().add(erlanggaDominance);

    UserSubcriteria erlanggaInfluence = new UserSubcriteria();
    erlanggaInfluence.setScore(4);
    erlanggaInfluence.setUser(erlangga);
    erlanggaInfluence.setSubcriteria(influence);
    erlangga.getUserSubcriterias().add(erlanggaInfluence);

    UserSubcriteria erlanggaSteadiness = new UserSubcriteria();
    erlanggaSteadiness.setScore(5);
    erlanggaSteadiness.setUser(erlangga);
    erlanggaSteadiness.setSubcriteria(steadiness);
    erlangga.getUserSubcriterias().add(erlanggaSteadiness);

    UserSubcriteria erlanggaCompliance = new UserSubcriteria();
    erlanggaCompliance.setScore(3);
    erlanggaCompliance.setUser(erlangga);
    erlanggaCompliance.setSubcriteria(compliance);
    erlangga.getUserSubcriterias().add(erlanggaCompliance);

    UserSubcriteria erlanggaPlaymusic = new UserSubcriteria();
    erlanggaPlaymusic.setScore(3);
    erlanggaPlaymusic.setUser(erlangga);
    erlanggaPlaymusic.setSubcriteria(playMusic);
    erlangga.getUserSubcriterias().add(erlanggaPlaymusic);

    UserSubcriteria erlanggaWorkout = new UserSubcriteria();
    erlanggaWorkout.setScore(3);
    erlanggaWorkout.setUser(erlangga);
    erlanggaWorkout.setSubcriteria(workout);
    erlangga.getUserSubcriterias().add(erlanggaWorkout);

    userService.save(erlangga);

    //----------------------------------------------------------//

    User vava = new User();
    vava.setUsername("vava");
    vava.setPassword("123");
    vava.setAge(41);
    vava.setEmail("vava@gmail.com");
    vava.setGender(Gender.MAN);
    vava.setHeight(177.0);
    vava.setName("Vava");
    vava.setPhone("08873627547");
    vava.setReligion(Religion.CHRISTIAN);
    vava.setWeight(81.0);

    UserSubcriteria vavaDominance = new UserSubcriteria();
    vavaDominance.setScore(3);
    vavaDominance.setUser(vava);
    vavaDominance.setSubcriteria(dominance);
    vava.getUserSubcriterias().add(vavaDominance);

    UserSubcriteria vavaInfluence = new UserSubcriteria();
    vavaInfluence.setScore(3);
    vavaInfluence.setUser(vava);
    vavaInfluence.setSubcriteria(influence);
    vava.getUserSubcriterias().add(vavaInfluence);

    UserSubcriteria vavaSteadiness = new UserSubcriteria();
    vavaSteadiness.setScore(3);
    vavaSteadiness.setUser(vava);
    vavaSteadiness.setSubcriteria(steadiness);
    vava.getUserSubcriterias().add(vavaSteadiness);

    UserSubcriteria vavaCompliance = new UserSubcriteria();
    vavaCompliance.setScore(3);
    vavaCompliance.setUser(vava);
    vavaCompliance.setSubcriteria(compliance);
    vava.getUserSubcriterias().add(vavaCompliance);

    UserSubcriteria vavaPlaymusic = new UserSubcriteria();
    vavaPlaymusic.setScore(1);
    vavaPlaymusic.setUser(vava);
    vavaPlaymusic.setSubcriteria(playMusic);
    vava.getUserSubcriterias().add(vavaPlaymusic);

    UserSubcriteria vavaWorkout = new UserSubcriteria();
    vavaWorkout.setScore(3);
    vavaWorkout.setUser(vava);
    vavaWorkout.setSubcriteria(workout);
    vava.getUserSubcriterias().add(vavaWorkout);

    userService.save(vava);

    //----------------------------------------------------------//

    User gita = new User();
    gita.setUsername("gita");
    gita.setPassword("123");
    gita.setAge(41);
    gita.setEmail("gita@gmail.com");
    gita.setGender(Gender.WOMAN);
    gita.setHeight(169.0);
    gita.setName("Gita");
    gita.setPhone("0856782973");
    gita.setReligion(Religion.BUDDHA);
    gita.setWeight(68.0);

    UserSubcriteria gitaDominance = new UserSubcriteria();
    gitaDominance.setScore(4);
    gitaDominance.setUser(gita);
    gitaDominance.setSubcriteria(dominance);
    gita.getUserSubcriterias().add(gitaDominance);

    UserSubcriteria gitaInfluence = new UserSubcriteria();
    gitaInfluence.setScore(5);
    gitaInfluence.setUser(gita);
    gitaInfluence.setSubcriteria(influence);
    gita.getUserSubcriterias().add(gitaInfluence);

    UserSubcriteria gitaSteadiness = new UserSubcriteria();
    gitaSteadiness.setScore(3);
    gitaSteadiness.setUser(gita);
    gitaSteadiness.setSubcriteria(steadiness);
    gita.getUserSubcriterias().add(gitaSteadiness);

    UserSubcriteria gitaCompliance = new UserSubcriteria();
    gitaCompliance.setScore(1);
    gitaCompliance.setUser(gita);
    gitaCompliance.setSubcriteria(compliance);
    gita.getUserSubcriterias().add(gitaCompliance);

    UserSubcriteria gitaPlaymusic = new UserSubcriteria();
    gitaPlaymusic.setScore(1);
    gitaPlaymusic.setUser(gita);
    gitaPlaymusic.setSubcriteria(playMusic);
    gita.getUserSubcriterias().add(gitaPlaymusic);

    UserSubcriteria gitaWorkout = new UserSubcriteria();
    gitaWorkout.setScore(5);
    gitaWorkout.setUser(gita);
    gitaWorkout.setSubcriteria(workout);
    gita.getUserSubcriterias().add(gitaWorkout);

    userService.save(gita);

    //----------------------------------------------------------//

    User david = new User();
    david.setUsername("david");
    david.setPassword("123");
    david.setAge(23);
    david.setEmail("david@gmail.com");
    david.setGender(Gender.MAN);
    david.setHeight(165.0);
    david.setName("David");
    david.setPhone("08778866453");
    david.setReligion(Religion.CATHOLIC);
    david.setWeight(65.0);

    UserSubcriteria davidDominance = new UserSubcriteria();
    davidDominance.setScore(4);
    davidDominance.setUser(david);
    davidDominance.setSubcriteria(dominance);
    david.getUserSubcriterias().add(davidDominance);

    UserSubcriteria davidInfluence = new UserSubcriteria();
    davidInfluence.setScore(4);
    davidInfluence.setUser(david);
    davidInfluence.setSubcriteria(influence);
    david.getUserSubcriterias().add(davidInfluence);

    UserSubcriteria davidSteadiness = new UserSubcriteria();
    davidSteadiness.setScore(4);
    davidSteadiness.setUser(david);
    davidSteadiness.setSubcriteria(steadiness);
    david.getUserSubcriterias().add(davidSteadiness);

    UserSubcriteria davidCompliance = new UserSubcriteria();
    davidCompliance.setScore(4);
    davidCompliance.setUser(david);
    davidCompliance.setSubcriteria(compliance);
    david.getUserSubcriterias().add(davidCompliance);

    UserSubcriteria davidPlaymusic = new UserSubcriteria();
    davidPlaymusic.setScore(1);
    davidPlaymusic.setUser(david);
    davidPlaymusic.setSubcriteria(playMusic);
    david.getUserSubcriterias().add(davidPlaymusic);

    UserSubcriteria davidWorkout = new UserSubcriteria();
    davidWorkout.setScore(4);
    davidWorkout.setUser(david);
    davidWorkout.setSubcriteria(workout);
    david.getUserSubcriterias().add(davidWorkout);

    userService.save(david);

    //----------------------------------------------------------//

    User ricky = new User();
    ricky.setUsername("ricky");
    ricky.setPassword("123");
    ricky.setAge(29);
    ricky.setEmail("ricky@gmail.com");
    ricky.setGender(Gender.MAN);
    ricky.setHeight(181.0);
    ricky.setName("Ricky");
    ricky.setPhone("08778866453");
    ricky.setReligion(Religion.CATHOLIC);
    ricky.setWeight(84.0);

    UserSubcriteria rickyDominance = new UserSubcriteria();
    rickyDominance.setScore(5);
    rickyDominance.setUser(ricky);
    rickyDominance.setSubcriteria(dominance);
    ricky.getUserSubcriterias().add(rickyDominance);

    UserSubcriteria rickyInfluence = new UserSubcriteria();
    rickyInfluence.setScore(2);
    rickyInfluence.setUser(ricky);
    rickyInfluence.setSubcriteria(influence);
    ricky.getUserSubcriterias().add(rickyInfluence);

    UserSubcriteria rickySteadiness = new UserSubcriteria();
    rickySteadiness.setScore(1);
    rickySteadiness.setUser(ricky);
    rickySteadiness.setSubcriteria(steadiness);
    ricky.getUserSubcriterias().add(rickySteadiness);

    UserSubcriteria rickyCompliance = new UserSubcriteria();
    rickyCompliance.setScore(2);
    rickyCompliance.setUser(ricky);
    rickyCompliance.setSubcriteria(compliance);
    ricky.getUserSubcriterias().add(rickyCompliance);

    UserSubcriteria rickyPlaymusic = new UserSubcriteria();
    rickyPlaymusic.setScore(2);
    rickyPlaymusic.setUser(ricky);
    rickyPlaymusic.setSubcriteria(playMusic);
    ricky.getUserSubcriterias().add(rickyPlaymusic);

    UserSubcriteria rickyWorkout = new UserSubcriteria();
    rickyWorkout.setScore(3);
    rickyWorkout.setUser(ricky);
    rickyWorkout.setSubcriteria(workout);
    ricky.getUserSubcriterias().add(rickyWorkout);

    userService.save(ricky);

    //----------------------------------------------------------//

    User adrian = new User();
    adrian.setUsername("adrian");
    adrian.setPassword("123");
    adrian.setAge(25);
    adrian.setEmail("adrian@gmail.com");
    adrian.setGender(Gender.MAN);
    adrian.setHeight(164.0);
    adrian.setName("Adrian");
    adrian.setPhone("08778866453");
    adrian.setReligion(Religion.BUDDHA);
    adrian.setWeight(68.0);

    UserSubcriteria adrianDominance = new UserSubcriteria();
    adrianDominance.setScore(3);
    adrianDominance.setUser(adrian);
    adrianDominance.setSubcriteria(dominance);
    adrian.getUserSubcriterias().add(adrianDominance);

    UserSubcriteria adrianInfluence = new UserSubcriteria();
    adrianInfluence.setScore(5);
    adrianInfluence.setUser(adrian);
    adrianInfluence.setSubcriteria(influence);
    adrian.getUserSubcriterias().add(adrianInfluence);

    UserSubcriteria adrianSteadiness = new UserSubcriteria();
    adrianSteadiness.setScore(4);
    adrianSteadiness.setUser(adrian);
    adrianSteadiness.setSubcriteria(steadiness);
    adrian.getUserSubcriterias().add(adrianSteadiness);

    UserSubcriteria adrianCompliance = new UserSubcriteria();
    adrianCompliance.setScore(4);
    adrianCompliance.setUser(adrian);
    adrianCompliance.setSubcriteria(compliance);
    adrian.getUserSubcriterias().add(adrianCompliance);

    UserSubcriteria adrianPlaymusic = new UserSubcriteria();
    adrianPlaymusic.setScore(5);
    adrianPlaymusic.setUser(adrian);
    adrianPlaymusic.setSubcriteria(playMusic);
    adrian.getUserSubcriterias().add(adrianPlaymusic);

    UserSubcriteria adrianWorkout = new UserSubcriteria();
    adrianWorkout.setScore(2);
    adrianWorkout.setUser(adrian);
    adrianWorkout.setSubcriteria(workout);
    adrian.getUserSubcriterias().add(adrianWorkout);

    userService.save(adrian);

    //----------------------------------------------------------//

    User reza = new User();
    reza.setUsername("reza");
    reza.setPassword("123");
    reza.setAge(22);
    reza.setEmail("reza@gmail.com");
    reza.setGender(Gender.MAN);
    reza.setHeight(175.0);
    reza.setName("Reza");
    reza.setPhone("08972254723");
    reza.setReligion(Religion.OTHER);
    reza.setWeight(68.0);

    UserSubcriteria rezaDominance = new UserSubcriteria();
    rezaDominance.setScore(2);
    rezaDominance.setUser(reza);
    rezaDominance.setSubcriteria(dominance);
    reza.getUserSubcriterias().add(rezaDominance);

    UserSubcriteria rezaInfluence = new UserSubcriteria();
    rezaInfluence.setScore(4);
    rezaInfluence.setUser(reza);
    rezaInfluence.setSubcriteria(influence);
    reza.getUserSubcriterias().add(rezaInfluence);

    UserSubcriteria rezaSteadiness = new UserSubcriteria();
    rezaSteadiness.setScore(3);
    rezaSteadiness.setUser(reza);
    rezaSteadiness.setSubcriteria(steadiness);
    reza.getUserSubcriterias().add(rezaSteadiness);

    UserSubcriteria rezaCompliance = new UserSubcriteria();
    rezaCompliance.setScore(1);
    rezaCompliance.setUser(reza);
    rezaCompliance.setSubcriteria(compliance);
    reza.getUserSubcriterias().add(rezaCompliance);

    UserSubcriteria rezaPlaymusic = new UserSubcriteria();
    rezaPlaymusic.setScore(4);
    rezaPlaymusic.setUser(reza);
    rezaPlaymusic.setSubcriteria(playMusic);
    reza.getUserSubcriterias().add(rezaPlaymusic);

    UserSubcriteria rezaWorkout = new UserSubcriteria();
    rezaWorkout.setScore(5);
    rezaWorkout.setUser(reza);
    rezaWorkout.setSubcriteria(workout);
    reza.getUserSubcriterias().add(rezaWorkout);

    userService.save(reza);

    //----------------------------------------------------------//

    User Brenda = new User();
    Brenda.setUsername("Brenda");
    Brenda.setPassword("123");
    Brenda.setAge(27);
    Brenda.setEmail("Brenda@gmail.Com");
    Brenda.setGender(Gender.MAN);
    Brenda.setHeight(180.0);
    Brenda.setName("Brenda");
    Brenda.setPhone("085600123");
    Brenda.setReligion(Religion.MOESLIM);
    Brenda.setWeight(75.0);

    UserSubcriteria BrendaDominance = new UserSubcriteria();
    BrendaDominance.setScore(2);
    BrendaDominance.setUser(Brenda);
    BrendaDominance.setSubcriteria(dominance);
    Brenda.getUserSubcriterias().add(BrendaDominance);

    UserSubcriteria BrendaInfluence = new UserSubcriteria();
    BrendaInfluence.setScore(3);
    BrendaInfluence.setUser(Brenda);
    BrendaInfluence.setSubcriteria(influence);
    Brenda.getUserSubcriterias().add(BrendaInfluence);

    UserSubcriteria BrendaSteadiness = new UserSubcriteria();
    BrendaSteadiness.setScore(4);
    BrendaSteadiness.setUser(Brenda);
    BrendaSteadiness.setSubcriteria(steadiness);
    Brenda.getUserSubcriterias().add(BrendaSteadiness);

    UserSubcriteria BrendaCompliance = new UserSubcriteria();
    BrendaCompliance.setScore(2);
    BrendaCompliance.setUser(Brenda);
    BrendaCompliance.setSubcriteria(compliance);
    Brenda.getUserSubcriterias().add(BrendaCompliance);

    UserSubcriteria BrendaPlaymusic = new UserSubcriteria();
    BrendaPlaymusic.setScore(5);
    BrendaPlaymusic.setUser(Brenda);
    BrendaPlaymusic.setSubcriteria(playMusic);
    Brenda.getUserSubcriterias().add(BrendaPlaymusic);

    UserSubcriteria BrendaWorkout = new UserSubcriteria();
    BrendaWorkout.setScore(5);
    BrendaWorkout.setUser(Brenda);
    BrendaWorkout.setSubcriteria(workout);
    Brenda.getUserSubcriterias().add(BrendaWorkout);

    userService.save(Brenda);

    //----------------------------------------------------------// 

    User Cinta = new User();
    Cinta.setUsername("Cinta");
    Cinta.setPassword("456");
    Cinta.setAge(23);
    Cinta.setEmail("Cinta@gmail.Com");
    Cinta.setGender(Gender.WOMAN);
    Cinta.setHeight(160.0);
    Cinta.setName("Cinta");
    Cinta.setPhone("0850123");
    Cinta.setReligion(Religion.CATHOLIC);
    Cinta.setWeight(50.0);

    UserSubcriteria CintaDominance = new UserSubcriteria();
    CintaDominance.setScore(1);
    CintaDominance.setUser(Cinta);
    CintaDominance.setSubcriteria(dominance);
    Cinta.getUserSubcriterias().add(CintaDominance);

    UserSubcriteria CintaInfluence = new UserSubcriteria();
    CintaInfluence.setScore(2);
    CintaInfluence.setUser(Cinta);
    CintaInfluence.setSubcriteria(influence);
    Cinta.getUserSubcriterias().add(CintaInfluence);

    UserSubcriteria CintaSteadiness = new UserSubcriteria();
    CintaSteadiness.setScore(5);
    CintaSteadiness.setUser(Cinta);
    CintaSteadiness.setSubcriteria(steadiness);
    Cinta.getUserSubcriterias().add(CintaSteadiness);

    UserSubcriteria CintaCompliance = new UserSubcriteria();
    CintaCompliance.setScore(4);
    CintaCompliance.setUser(Cinta);
    CintaCompliance.setSubcriteria(compliance);
    Cinta.getUserSubcriterias().add(CintaCompliance);

    UserSubcriteria CintaPlaymusic = new UserSubcriteria();
    CintaPlaymusic.setScore(5);
    CintaPlaymusic.setUser(Cinta);
    CintaPlaymusic.setSubcriteria(playMusic);
    Cinta.getUserSubcriterias().add(CintaPlaymusic);

    UserSubcriteria CintaWorkout = new UserSubcriteria();
    CintaWorkout.setScore(1);
    CintaWorkout.setUser(Cinta);
    CintaWorkout.setSubcriteria(workout);
    Cinta.getUserSubcriterias().add(CintaWorkout);

    userService.save(Cinta);

    //----------------------------------------------------------//

    User Bryan = new User();
    Bryan.setUsername("Bryan");
    Bryan.setPassword("12367");
    Bryan.setAge(27);
    Bryan.setEmail("Bryan@gmail.Com");
    Bryan.setGender(Gender.MAN);
    Bryan.setHeight(173.0);
    Bryan.setName("Bryan");
    Bryan.setPhone("0856001423");
    Bryan.setReligion(Religion.CATHOLIC);
    Bryan.setWeight(80.0);

    UserSubcriteria BryanDominance = new UserSubcriteria();
    BryanDominance.setScore(5);
    BryanDominance.setUser(Bryan);
    BryanDominance.setSubcriteria(dominance);
    Bryan.getUserSubcriterias().add(BryanDominance);

    UserSubcriteria BryanInfluence = new UserSubcriteria();
    BryanInfluence.setScore(5);
    BryanInfluence.setUser(Bryan);
    BryanInfluence.setSubcriteria(influence);
    Bryan.getUserSubcriterias().add(BryanInfluence);

    UserSubcriteria BryanSteadiness = new UserSubcriteria();
    BryanSteadiness.setScore(2);
    BryanSteadiness.setUser(Bryan);
    BryanSteadiness.setSubcriteria(steadiness);
    Bryan.getUserSubcriterias().add(BryanSteadiness);

    UserSubcriteria BryanCompliance = new UserSubcriteria();
    BryanCompliance.setScore(2);
    BryanCompliance.setUser(Bryan);
    BryanCompliance.setSubcriteria(compliance);
    Bryan.getUserSubcriterias().add(BryanCompliance);

    UserSubcriteria BryanPlaymusic = new UserSubcriteria();
    BryanPlaymusic.setScore(3);
    BryanPlaymusic.setUser(Bryan);
    BryanPlaymusic.setSubcriteria(playMusic);
    Bryan.getUserSubcriterias().add(BryanPlaymusic);

    UserSubcriteria BryanWorkout = new UserSubcriteria();
    BryanWorkout.setScore(3);
    BryanWorkout.setUser(Bryan);
    BryanWorkout.setSubcriteria(workout);
    Bryan.getUserSubcriterias().add(BryanWorkout);

    userService.save(Bryan);

    //----------------------------------------------------------//

    User Rida = new User();
    Rida.setUsername("Rida");
    Rida.setPassword("123");
    Rida.setAge(29);
    Rida.setEmail("Rida@gmail.Com");
    Rida.setGender(Gender.WOMAN);
    Rida.setHeight(155.0);
    Rida.setName("Rida");
    Rida.setPhone("0859070123");
    Rida.setReligion(Religion.CHRISTIAN);
    Rida.setWeight(43.0);

    UserSubcriteria RidaDominance = new UserSubcriteria();
    RidaDominance.setScore(3);
    RidaDominance.setUser(Rida);
    RidaDominance.setSubcriteria(dominance);
    Rida.getUserSubcriterias().add(RidaDominance);

    UserSubcriteria RidaInfluence = new UserSubcriteria();
    RidaInfluence.setScore(3);
    RidaInfluence.setUser(Rida);
    RidaInfluence.setSubcriteria(influence);
    Rida.getUserSubcriterias().add(RidaInfluence);

    UserSubcriteria RidaSteadiness = new UserSubcriteria();
    RidaSteadiness.setScore(3);
    RidaSteadiness.setUser(Rida);
    RidaSteadiness.setSubcriteria(steadiness);
    Rida.getUserSubcriterias().add(RidaSteadiness);

    UserSubcriteria RidaCompliance = new UserSubcriteria();
    RidaCompliance.setScore(4);
    RidaCompliance.setUser(Rida);
    RidaCompliance.setSubcriteria(compliance);
    Rida.getUserSubcriterias().add(RidaCompliance);

    UserSubcriteria RidaPlaymusic = new UserSubcriteria();
    RidaPlaymusic.setScore(2);
    RidaPlaymusic.setUser(Rida);
    RidaPlaymusic.setSubcriteria(playMusic);
    Rida.getUserSubcriterias().add(RidaPlaymusic);

    UserSubcriteria RidaWorkout = new UserSubcriteria();
    RidaWorkout.setScore(3);
    RidaWorkout.setUser(Rida);
    RidaWorkout.setSubcriteria(workout);
    Rida.getUserSubcriterias().add(RidaWorkout);

    userService.save(Rida);

    //----------------------------------------------------------//

    User Joko = new User();
    Joko.setUsername("Joko");
    Joko.setPassword("123");
    Joko.setAge(32);
    Joko.setEmail("Joko@gmail.Com");
    Joko.setGender(Gender.MAN);
    Joko.setHeight(168.0);
    Joko.setName("Joko");
    Joko.setPhone("08565400123");
    Joko.setReligion(Religion.OTHER);
    Joko.setWeight(65.0);

    UserSubcriteria JokoDominance = new UserSubcriteria();
    JokoDominance.setScore(5);
    JokoDominance.setUser(Joko);
    JokoDominance.setSubcriteria(dominance);
    Joko.getUserSubcriterias().add(JokoDominance);

    UserSubcriteria JokoInfluence = new UserSubcriteria();
    JokoInfluence.setScore(2);
    JokoInfluence.setUser(Joko);
    JokoInfluence.setSubcriteria(influence);
    Joko.getUserSubcriterias().add(JokoInfluence);

    UserSubcriteria JokoSteadiness = new UserSubcriteria();
    JokoSteadiness.setScore(3);
    JokoSteadiness.setUser(Joko);
    JokoSteadiness.setSubcriteria(steadiness);
    Joko.getUserSubcriterias().add(JokoSteadiness);

    UserSubcriteria JokoCompliance = new UserSubcriteria();
    JokoCompliance.setScore(2);
    JokoCompliance.setUser(Joko);
    JokoCompliance.setSubcriteria(compliance);
    Joko.getUserSubcriterias().add(JokoCompliance);

    UserSubcriteria JokoPlaymusic = new UserSubcriteria();
    JokoPlaymusic.setScore(1);
    JokoPlaymusic.setUser(Joko);
    JokoPlaymusic.setSubcriteria(playMusic);
    Joko.getUserSubcriterias().add(JokoPlaymusic);

    UserSubcriteria JokoWorkout = new UserSubcriteria();
    JokoWorkout.setScore(5);
    JokoWorkout.setUser(Joko);
    JokoWorkout.setSubcriteria(workout);
    Joko.getUserSubcriterias().add(JokoWorkout);

    userService.save(Joko);

    //----------------------------------------------------------//

    User Sinta = new User();
    Sinta.setUsername("Sinta");
    Sinta.setPassword("12qwe3");
    Sinta.setAge(25);
    Sinta.setEmail("Sinta@gmail.Com");
    Sinta.setGender(Gender.WOMAN);
    Sinta.setHeight(163.0);
    Sinta.setName("Sinta");
    Sinta.setPhone("081235600123");
    Sinta.setReligion(Religion.MOESLIM);
    Sinta.setWeight(64.0);

    UserSubcriteria SintaDominance = new UserSubcriteria();
    SintaDominance.setScore(2);
    SintaDominance.setUser(Sinta);
    SintaDominance.setSubcriteria(dominance);
    Sinta.getUserSubcriterias().add(SintaDominance);

    UserSubcriteria SintaInfluence = new UserSubcriteria();
    SintaInfluence.setScore(3);
    SintaInfluence.setUser(Sinta);
    SintaInfluence.setSubcriteria(influence);
    Sinta.getUserSubcriterias().add(SintaInfluence);

    UserSubcriteria SintaSteadiness = new UserSubcriteria();
    SintaSteadiness.setScore(4);
    SintaSteadiness.setUser(Sinta);
    SintaSteadiness.setSubcriteria(steadiness);
    Sinta.getUserSubcriterias().add(SintaSteadiness);

    UserSubcriteria SintaCompliance = new UserSubcriteria();
    SintaCompliance.setScore(4);
    SintaCompliance.setUser(Sinta);
    SintaCompliance.setSubcriteria(compliance);
    Sinta.getUserSubcriterias().add(SintaCompliance);

    UserSubcriteria SintaPlaymusic = new UserSubcriteria();
    SintaPlaymusic.setScore(3);
    SintaPlaymusic.setUser(Sinta);
    SintaPlaymusic.setSubcriteria(playMusic);
    Sinta.getUserSubcriterias().add(SintaPlaymusic);

    UserSubcriteria SintaWorkout = new UserSubcriteria();
    SintaWorkout.setScore(4);
    SintaWorkout.setUser(Sinta);
    SintaWorkout.setSubcriteria(workout);
    Sinta.getUserSubcriterias().add(SintaWorkout);

    userService.save(Sinta);

    //----------------------------------------------------------//

    User James = new User();
    James.setUsername("James");
    James.setPassword("12gfd3");
    James.setAge(23);
    James.setEmail("James@gmail.Com");
    James.setGender(Gender.MAN);
    James.setHeight(174.0);
    James.setName("James");
    James.setPhone("085980123");
    James.setReligion(Religion.CATHOLIC);
    James.setWeight(77.0);

    UserSubcriteria JamesDominance = new UserSubcriteria();
    JamesDominance.setScore(4);
    JamesDominance.setUser(James);
    JamesDominance.setSubcriteria(dominance);
    James.getUserSubcriterias().add(JamesDominance);

    UserSubcriteria JamesInfluence = new UserSubcriteria();
    JamesInfluence.setScore(5);
    JamesInfluence.setUser(James);
    JamesInfluence.setSubcriteria(influence);
    James.getUserSubcriterias().add(JamesInfluence);

    UserSubcriteria JamesSteadiness = new UserSubcriteria();
    JamesSteadiness.setScore(2);
    JamesSteadiness.setUser(James);
    JamesSteadiness.setSubcriteria(steadiness);
    James.getUserSubcriterias().add(JamesSteadiness);

    UserSubcriteria JamesCompliance = new UserSubcriteria();
    JamesCompliance.setScore(1);
    JamesCompliance.setUser(James);
    JamesCompliance.setSubcriteria(compliance);
    James.getUserSubcriterias().add(JamesCompliance);

    UserSubcriteria JamesPlaymusic = new UserSubcriteria();
    JamesPlaymusic.setScore(5);
    JamesPlaymusic.setUser(James);
    JamesPlaymusic.setSubcriteria(playMusic);
    James.getUserSubcriterias().add(JamesPlaymusic);

    UserSubcriteria JamesWorkout = new UserSubcriteria();
    JamesWorkout.setScore(5);
    JamesWorkout.setUser(James);
    JamesWorkout.setSubcriteria(workout);
    James.getUserSubcriterias().add(JamesWorkout);

    userService.save(James);

    //----------------------------------------------------------//

    User Dessy = new User();
    Dessy.setUsername("Dessy");
    Dessy.setPassword("123654");
    Dessy.setAge(22);
    Dessy.setEmail("Dessy@gmail.Com");
    Dessy.setGender(Gender.WOMAN);
    Dessy.setHeight(155.0);
    Dessy.setName("Dessy");
    Dessy.setPhone("08566700123");
    Dessy.setReligion(Religion.BUDDHA);
    Dessy.setWeight(43.0);

    UserSubcriteria DessyDominance = new UserSubcriteria();
    DessyDominance.setScore(5);
    DessyDominance.setUser(Dessy);
    DessyDominance.setSubcriteria(dominance);
    Dessy.getUserSubcriterias().add(DessyDominance);

    UserSubcriteria DessyInfluence = new UserSubcriteria();
    DessyInfluence.setScore(4);
    DessyInfluence.setUser(Dessy);
    DessyInfluence.setSubcriteria(influence);
    Dessy.getUserSubcriterias().add(DessyInfluence);

    UserSubcriteria DessySteadiness = new UserSubcriteria();
    DessySteadiness.setScore(3);
    DessySteadiness.setUser(Dessy);
    DessySteadiness.setSubcriteria(steadiness);
    Dessy.getUserSubcriterias().add(DessySteadiness);

    UserSubcriteria DessyCompliance = new UserSubcriteria();
    DessyCompliance.setScore(2);
    DessyCompliance.setUser(Dessy);
    DessyCompliance.setSubcriteria(compliance);
    Dessy.getUserSubcriterias().add(DessyCompliance);

    UserSubcriteria DessyPlaymusic = new UserSubcriteria();
    DessyPlaymusic.setScore(3);
    DessyPlaymusic.setUser(Dessy);
    DessyPlaymusic.setSubcriteria(playMusic);
    Dessy.getUserSubcriterias().add(DessyPlaymusic);

    UserSubcriteria DessyWorkout = new UserSubcriteria();
    DessyWorkout.setScore(4);
    DessyWorkout.setUser(Dessy);
    DessyWorkout.setSubcriteria(workout);
    Dessy.getUserSubcriterias().add(DessyWorkout);

    userService.save(Dessy);

    //----------------------------------------------------------//

    User Nico = new User();
    Nico.setUsername("Nico");
    Nico.setPassword("12398");
    Nico.setAge(26);
    Nico.setEmail("Nico@gmail.Com");
    Nico.setGender(Gender.MAN);
    Nico.setHeight(177.0);
    Nico.setName("Nico");
    Nico.setPhone("089540163");
    Nico.setReligion(Religion.MOESLIM);
    Nico.setWeight(83.0);

    UserSubcriteria NicoDominance = new UserSubcriteria();
    NicoDominance.setScore(2);
    NicoDominance.setUser(Nico);
    NicoDominance.setSubcriteria(dominance);
    Nico.getUserSubcriterias().add(NicoDominance);

    UserSubcriteria NicoInfluence = new UserSubcriteria();
    NicoInfluence.setScore(3);
    NicoInfluence.setUser(Nico);
    NicoInfluence.setSubcriteria(influence);
    Nico.getUserSubcriterias().add(NicoInfluence);

    UserSubcriteria NicoSteadiness = new UserSubcriteria();
    NicoSteadiness.setScore(5);
    NicoSteadiness.setUser(Nico);
    NicoSteadiness.setSubcriteria(steadiness);
    Nico.getUserSubcriterias().add(NicoSteadiness);

    UserSubcriteria NicoCompliance = new UserSubcriteria();
    NicoCompliance.setScore(4);
    NicoCompliance.setUser(Nico);
    NicoCompliance.setSubcriteria(compliance);
    Nico.getUserSubcriterias().add(NicoCompliance);

    UserSubcriteria NicoPlaymusic = new UserSubcriteria();
    NicoPlaymusic.setScore(1);
    NicoPlaymusic.setUser(Nico);
    NicoPlaymusic.setSubcriteria(playMusic);
    Nico.getUserSubcriterias().add(NicoPlaymusic);

    UserSubcriteria NicoWorkout = new UserSubcriteria();
    NicoWorkout.setScore(3);
    NicoWorkout.setUser(Nico);
    NicoWorkout.setSubcriteria(workout);
    Nico.getUserSubcriterias().add(NicoWorkout);

    userService.save(Nico);

    //----------------------------------------------------------//

    User Astari = new User();
    Astari.setUsername("Astari");
    Astari.setPassword("1231234");
    Astari.setAge(21);
    Astari.setEmail("Astari@gmail.Com");
    Astari.setGender(Gender.WOMAN);
    Astari.setHeight(162.0);
    Astari.setName("Astari");
    Astari.setPhone("0845670123");
    Astari.setReligion(Religion.MOESLIM);
    Astari.setWeight(55.0);

    UserSubcriteria AstariDominance = new UserSubcriteria();
    AstariDominance.setScore(4);
    AstariDominance.setUser(Astari);
    AstariDominance.setSubcriteria(dominance);
    Astari.getUserSubcriterias().add(AstariDominance);

    UserSubcriteria AstariInfluence = new UserSubcriteria();
    AstariInfluence.setScore(4);
    AstariInfluence.setUser(Astari);
    AstariInfluence.setSubcriteria(influence);
    Astari.getUserSubcriterias().add(AstariInfluence);

    UserSubcriteria AstariSteadiness = new UserSubcriteria();
    AstariSteadiness.setScore(3);
    AstariSteadiness.setUser(Astari);
    AstariSteadiness.setSubcriteria(steadiness);
    Astari.getUserSubcriterias().add(AstariSteadiness);

    UserSubcriteria AstariCompliance = new UserSubcriteria();
    AstariCompliance.setScore(1);
    AstariCompliance.setUser(Astari);
    AstariCompliance.setSubcriteria(compliance);
    Astari.getUserSubcriterias().add(AstariCompliance);

    UserSubcriteria AstariPlaymusic = new UserSubcriteria();
    AstariPlaymusic.setScore(2);
    AstariPlaymusic.setUser(Astari);
    AstariPlaymusic.setSubcriteria(playMusic);
    Astari.getUserSubcriterias().add(AstariPlaymusic);

    UserSubcriteria AstariWorkout = new UserSubcriteria();
    AstariWorkout.setScore(5);
    AstariWorkout.setUser(Astari);
    AstariWorkout.setSubcriteria(workout);
    Astari.getUserSubcriterias().add(AstariWorkout);

    userService.save(Astari);

    //----------------------------------------------------------//

    User Taufik = new User();
    Taufik.setUsername("Taufik");
    Taufik.setPassword("123");
    Taufik.setAge(36);
    Taufik.setEmail("Taufik@gmail.Com");
    Taufik.setGender(Gender.MAN);
    Taufik.setHeight(170.0);
    Taufik.setName("Taufik");
    Taufik.setPhone("0845670123");
    Taufik.setReligion(Religion.CATHOLIC);
    Taufik.setWeight(74.0);

    UserSubcriteria TaufikDominance = new UserSubcriteria();
    TaufikDominance.setScore(4);
    TaufikDominance.setUser(Taufik);
    TaufikDominance.setSubcriteria(dominance);
    Taufik.getUserSubcriterias().add(TaufikDominance);

    UserSubcriteria TaufikInfluence = new UserSubcriteria();
    TaufikInfluence.setScore(4);
    TaufikInfluence.setUser(Taufik);
    TaufikInfluence.setSubcriteria(influence);
    Taufik.getUserSubcriterias().add(TaufikInfluence);

    UserSubcriteria TaufikSteadiness = new UserSubcriteria();
    TaufikSteadiness.setScore(3);
    TaufikSteadiness.setUser(Taufik);
    TaufikSteadiness.setSubcriteria(steadiness);
    Taufik.getUserSubcriterias().add(TaufikSteadiness);

    UserSubcriteria TaufikCompliance = new UserSubcriteria();
    TaufikCompliance.setScore(1);
    TaufikCompliance.setUser(Taufik);
    TaufikCompliance.setSubcriteria(compliance);
    Taufik.getUserSubcriterias().add(TaufikCompliance);

    UserSubcriteria TaufikPlaymusic = new UserSubcriteria();
    TaufikPlaymusic.setScore(1);
    TaufikPlaymusic.setUser(Taufik);
    TaufikPlaymusic.setSubcriteria(playMusic);
    Taufik.getUserSubcriterias().add(TaufikPlaymusic);

    UserSubcriteria TaufikWorkout = new UserSubcriteria();
    TaufikWorkout.setScore(4);
    TaufikWorkout.setUser(Taufik);
    TaufikWorkout.setSubcriteria(workout);
    Taufik.getUserSubcriterias().add(TaufikWorkout);

    userService.save(Taufik);

    //----------------------------------------------------------//

    User Nicolas = new User();
    Nicolas.setUsername("Nicolas");
    Nicolas.setPassword("123");
    Nicolas.setAge(34);
    Nicolas.setEmail("Nicolas@gmail.Com");
    Nicolas.setGender(Gender.MAN);
    Nicolas.setHeight(162.0);
    Nicolas.setName("Nicolas");
    Nicolas.setPhone("0845670123");
    Nicolas.setReligion(Religion.BUDDHA);
    Nicolas.setWeight(74.0);

    UserSubcriteria NicolasDominance = new UserSubcriteria();
    NicolasDominance.setScore(3);
    NicolasDominance.setUser(Nicolas);
    NicolasDominance.setSubcriteria(dominance);
    Nicolas.getUserSubcriterias().add(NicolasDominance);

    UserSubcriteria Nicolasnfluence = new UserSubcriteria();
    Nicolasnfluence.setScore(5);
    Nicolasnfluence.setUser(Nicolas);
    Nicolasnfluence.setSubcriteria(influence);
    Nicolas.getUserSubcriterias().add(Nicolasnfluence);

    UserSubcriteria NicolasSteadiness = new UserSubcriteria();
    NicolasSteadiness.setScore(3);
    NicolasSteadiness.setUser(Nicolas);
    NicolasSteadiness.setSubcriteria(steadiness);
    Nicolas.getUserSubcriterias().add(NicolasSteadiness);

    UserSubcriteria NicolasCompliance = new UserSubcriteria();
    NicolasCompliance.setScore(3);
    NicolasCompliance.setUser(Nicolas);
    NicolasCompliance.setSubcriteria(compliance);
    Nicolas.getUserSubcriterias().add(NicolasCompliance);

    UserSubcriteria NicolasPlaymusic = new UserSubcriteria();
    NicolasPlaymusic.setScore(4);
    NicolasPlaymusic.setUser(Nicolas);
    NicolasPlaymusic.setSubcriteria(playMusic);
    Nicolas.getUserSubcriterias().add(NicolasPlaymusic);

    UserSubcriteria NicolasWorkout = new UserSubcriteria();
    NicolasWorkout.setScore(2);
    NicolasWorkout.setUser(Nicolas);
    NicolasWorkout.setSubcriteria(workout);
    Nicolas.getUserSubcriterias().add(NicolasWorkout);

    userService.save(Nicolas);
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    if (userService.findAll().size() <= 0) {
      loadCriteriaAndSubCriteria();
      loadUserAndContent();
    }
  }
}
