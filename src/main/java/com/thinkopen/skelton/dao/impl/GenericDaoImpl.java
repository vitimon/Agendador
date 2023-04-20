package com.thinkopen.skelton.dao.impl;





import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.thinkopen.skelton.dao.GenericDao;



public abstract class GenericDaoImpl<T, I extends Serializable>  implements GenericDao<T, I> {
	@Autowired
	SessionFactory sessionFactory;
	private Class<T> type;
	
	public GenericDaoImpl(Class<T> type) {
		this.type = type;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	   
	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
	   
	@Transactional(readOnly = true)
	public T find(I id) {
	    return (T)getSessionFactory().getCurrentSession().get(this.type, (Serializable)id);
	 }
	 
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<T> findAll() {
	     return getSessionFactory().getCurrentSession().createQuery("from " + this.type.getName()).list();
	}
	    
	@Transactional
	public void delete(T obj) {
	     getSessionFactory().getCurrentSession().delete(obj);
	}
		
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<T> findByCriteria(Map<String, Object> whereCriteria) {
		return this.transformRequestToJpaQuery(whereCriteria, null, null)					   
				   .getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<T> findByCriteriaOrdered(Map<String, Object> whereCriteria, Map<String, Boolean> order) {
		return this.transformRequestToJpaQuery(whereCriteria, order, null).getResultList();
	}
	@Transactional
	public T saveOrUpdate(T obj) {
		getSessionFactory().getCurrentSession().saveOrUpdate(obj);
		return obj;
	}

	@Transactional
	public void evict(T obj) {
		getSessionFactory().getCurrentSession().evict(obj);
	}			
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<T> findAllWithPagination(int start, int pageSize) {	
		return getSessionFactory().getCurrentSession()
					.createQuery("from " + this.type.getName())
					.setFirstResult(start)
					.setMaxResults(pageSize)
					.list();

	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<T> findByCriteriaOrdered(int start, int pageSize, Map<String, Object> whereCriteria, Map<String, Boolean> order) {
		return this.transformRequestToJpaQuery(whereCriteria, order, null)
				   .setFirstResult(start)
				   .setMaxResults(pageSize)
				   .getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<T> findByAdvancedCriteriaOrdered(int start, int pageSize, Map<String, Boolean> order, Map<String, Object> whereCriteria, List<String> sqlRestrictions) {
		return this.transformRequestToJpaQuery(whereCriteria, order, sqlRestrictions)
				   .setFirstResult(start)
				   .setMaxResults(pageSize)
				   .getResultList();
	}

	/**
	 * Transform maps to hql
	 * NB i wrote it with criteria builder and all fuckin hibernate 5 stuf but go to the hell
	 * @param whereCriteria	
	 * @param order
	 * @param sqlRestrictions
	 * @return
	 */
	private Query transformRequestToJpaQuery( Map<String, Object> whereCriteria,Map<String, Boolean> order,List<String> sqlRestrictions) {
	
		String hql = "from " + this.type.getName()+" where ";
		if(whereCriteria != null) {
			int counter=0;
		    for (Map.Entry<String,Object> entry : whereCriteria.entrySet()) {
		    	if(counter > 0) {
		    		hql += " and ";
		    	}
	            hql += entry.getKey()+ " = :value"+counter;
		    	++counter;
	    	}
		}
		int counter = 0;
		if(sqlRestrictions != null) {
			
		    for (String item : sqlRestrictions) {
		    	if(counter > 0) {
		    		hql += " and ";
		    	}
	            hql += item;		
	            ++counter;
	    	}
		}				
		
		if(order != null && order.isEmpty() == false) {
			counter = 0;
			hql += " order by ";	
			for (Map.Entry<String, Boolean> item : order.entrySet()) {
				if(counter > 0) {
					hql+=",";
				}
				hql += item.getKey()+" "+(item.getValue() ? "ASC":"DESC");
				++counter;
			}					
		}				 
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);				
		if(whereCriteria != null && whereCriteria.isEmpty() == false) {
			counter=0;
		    for (Map.Entry<String,Object> entry : whereCriteria.entrySet()) {
	           query.setParameter("value"+counter,entry.getValue());
	           ++counter;
	    	}
		}
		return query;				
	}

}


