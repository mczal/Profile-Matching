package com.spk.dao;

import com.spk.model.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Gl552 on 4/25/2017.
 */
public interface CriteriaDao extends JpaRepository<Criteria, String> {
  
}
