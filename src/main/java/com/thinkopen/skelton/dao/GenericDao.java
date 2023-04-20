package com.thinkopen.skelton.dao;

import java.util.List;
import java.util.Map;
import org.hibernate.SessionFactory;

public interface GenericDao<T, I extends java.io.Serializable> {
 
  public SessionFactory getSessionFactory();
  
  public T find(I paramI);
  
  public List<T> findAll();
  
  public void delete(T paramT);
  
  public T saveOrUpdate(T paramT);
  
  public void evict(T paramT);
  
  public List<T> findByCriteria(Map<String, Object> paramMap);
  
  public List<T> findByCriteriaOrdered(Map<String, Object> paramMap, Map<String, Boolean> paramMap1);
  
  public List<T> findByCriteriaOrdered(int paramInt1, int paramInt2, Map<String, Object> paramMap, Map<String, Boolean> paramMap1);
  
  public List<T> findAllWithPagination(int paramInt1, int paramInt2);
  
}


