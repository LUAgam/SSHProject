/**

* Title: GeneralDaoImpl.java

* Description: 

* Copyright: Copyright (c) 2017

* @author AMGuo

* @date 2017年8月9日

* @version 1.0

*/
package com.aomen.guo.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aomen.guo.entity.BaseEntity;

/**
 * 
 * Title: GeneralDaoImpl
 * 
 * Description:
 * 
 * @author AMGuo
 * 
 * @date 2017年8月9日
 * 
 */
@Repository(value = "generalDao")
public class GeneralDaoImpl {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional(readOnly = true)
	public BaseEntity findOne(Object clazz, Long id) {
		String hql = "SELECT entity FROM " + clazz.getClass().getSimpleName() + " entity WHERE entity.id = ?";
		Session session = sessionFactory.getCurrentSession();
		session.evict(clazz);
		Transaction transaction = sessionFactory.getCurrentSession().getTransaction();
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		BaseEntity t = (BaseEntity) query.uniqueResult();
		session.evict(t);
		return t;
	}
}
