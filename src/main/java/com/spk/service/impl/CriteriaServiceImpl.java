package com.spk.service.impl;

import com.spk.dao.CriteriaDao;
import com.spk.model.Criteria;
import com.spk.service.CriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Gl552 on 4/25/2017.
 */
@Service
@Transactional(readOnly = true)
public class CriteriaServiceImpl implements CriteriaService {

  @Autowired
  private CriteriaDao criteriaDao;

  @Override
  @Transactional(readOnly = false)
  public void delete(String id) {
    criteriaDao.delete(id);
  }

  @Override
  public List<Criteria> findAll() {
    return criteriaDao.findAll();
  }

  @Override
  public Criteria findOne(String id) {
    return criteriaDao.findOne(id);
  }

  @Override
  @Transactional(readOnly = false)
  public Criteria save(Criteria domain) {
    return criteriaDao.save(domain);
  }
}
