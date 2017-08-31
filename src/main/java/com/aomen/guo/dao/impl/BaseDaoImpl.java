/**

* Title: BaseDaoImpl.java

* Description: 

* Copyright: Copyright (c) 2017

* @author AMGuo

* @date 2017年8月10日

* @version 1.0

*/
package com.aomen.guo.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.aomen.guo.dao.BaseDao;
import com.aomen.guo.util.ReflectionUtils;

/**
 * 
 * Title: BaseDaoImpl
 * 
 * Description:
 * 
 * @author AMGuo
 * 
 * @date 2017年8月10日
 * 
 */
public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {

    @PersistenceContext
	private EntityManager entityManager;

	private Class<T> entityClass;

	public void setEntityClass(Class<T> classzz) {
		this.entityClass = classzz;
	}

	public Class<T> getEntityClass() {
		return this.entityClass;
	}

	protected BaseDaoImpl() {
		this.entityClass = (Class<T>) ReflectionUtils.getSuperClassGenricType(getClass(), 0);
	}

	private Session getCurrentSession() {
		return this.entityManager.unwrap(org.hibernate.Session.class);
	}

	@Override
	public T load(PK id) {
		return (T) this.getCurrentSession().load(entityClass, id);
	}

	@Override
	public T get(PK id) {
		return (T) this.getCurrentSession().get(entityClass, id);
	}

	@Override
	public List<T> findAll() {
		return this.getCurrentSession().createQuery("from " + entityClass.getSimpleName()).list();
	}

	@Override
	public PK save(T entity) {
		return (PK) this.getCurrentSession().save(entity);
	}

	@Override
	public void saveOrUpdate(T entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(PK id) {
		this.getCurrentSession().delete(get(id));
	}

	@Override
	public void flush() {
		this.getCurrentSession().flush();
	}

}
