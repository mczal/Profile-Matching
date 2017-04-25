package com.spk.service;

import java.util.List;

/**
 * Created by Gl552 on 4/25/2017.
 */
public interface BaseService<T> {
  void delete(String id);

  List<T> findAll();

  T findOne(String id);

  T save(T domain);
}
