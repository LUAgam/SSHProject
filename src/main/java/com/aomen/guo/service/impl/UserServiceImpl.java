/**

* Title: UserServiceImpl.java

* Description: 

* Copyright: Copyright (c) 2017

* @author AMGuo

* @date 2017��8��7��

* @version 1.0

*/
package com.aomen.guo.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aomen.guo.dao.impl.UserDaoImpl;
import com.aomen.guo.entity.User;

/**
 * 
 * Title: UserServiceImpl
 * 
 * Description:
 * 
 * @author AMGuo
 * 
 * @date 2017��8��7��
 * 
 */
@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl {

	@Autowired
	private UserDaoImpl userDao;

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * ���� Javadoc��
	 * 
	 * Title: load
	 * 
	 * Description:
	 * 
	 * @param id
	 * @return
	 * 
	 * @see com.aomen.guo.service.UserService#load(java.lang.String)
	 * 
	 */
	public User load(Long id) {
		return userDao.load(id);
	}

	/**
	 * ���� Javadoc��
	 * 
	 * Title: get
	 * 
	 * Description:
	 * 
	 * @param id
	 * @return
	 * 
	 * @see com.aomen.guo.service.UserService#get(java.lang.String)
	 * 
	 */
	/*@Cacheable(value = "shortTimeCache", key = "'get'+#id")*/
	public User get(Long id) {
		return userDao.get(id);
	}

	/**
	 * ���� Javadoc��
	 * 
	 * Title: findAll
	 * 
	 * Description:
	 * 
	 * @return
	 * 
	 * @see com.aomen.guo.service.UserService#findAll()
	 * 
	 */
	public List<User> findAll() {
		Transaction transaction = sessionFactory.getCurrentSession().getTransaction();
		System.err.println(sessionFactory.getCurrentSession().getTransaction());
		return userDao.findAll();
	}

	/**
	 * ���� Javadoc��
	 * 
	 * Title: save
	 * 
	 * Description:
	 * 
	 * @param entity
	 * @return
	 * 
	 * @see com.aomen.guo.service.UserService#save(com.aomen.guo.entity.User)
	 * 
	 */
	/*
	 * @Transactional(readOnly = false,isolation =
	 * Isolation.SERIALIZABLE,propagation = Propagation.REQUIRED)
	 */
	@Transactional(readOnly = false)
	public Long save(User entity) {
		return userDao.save(entity);
	}

	/**
	 * ���� Javadoc��
	 * 
	 * Title: saveOrUpdate
	 * 
	 * Description:
	 * 
	 * @param entity
	 * 
	 * @see com.aomen.guo.service.UserService#saveOrUpdate(com.aomen.guo.entity.User)
	 * 
	 */
	@Transactional(readOnly = false)
	public void saveOrUpdate(User entity) {
		userDao.saveOrUpdate(entity);
	}

	/**
	 * ���� Javadoc��
	 * 
	 * Title: delete
	 * 
	 * Description:
	 * 
	 * @param id
	 * 
	 * @see com.aomen.guo.service.UserService#delete(java.lang.String)
	 * 
	 */
	@Transactional(readOnly = false)
	public void delete(Long id) {
		userDao.delete(id);
	}

	/**
	 * ���� Javadoc��
	 * 
	 * Title: flush
	 * 
	 * Description:
	 * 
	 * 
	 * @see com.aomen.guo.service.UserService#flush()
	 * 
	 */
	public void flush() {
		userDao.flush();
	}

}
