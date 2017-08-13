/**

* Title: RedisService.java

* Description: 

* Copyright: Copyright (c) 2017

* @author AMGuo

* @date 2017年8月13日

* @version 1.0

*/
package com.aomen.guo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aomen.guo.dao.impl.RedisDaoImpl;
import com.aomen.guo.entity.User;

/**
 * 
 * Title: RedisService
 * 
 * Description:
 * 
 * @author AMGuo
 * 
 * @date 2017年8月13日
 * 
 */
@Service("redisService")
public class RedisService {

	@Autowired
	RedisDaoImpl redisDao;

	public void put(User entity) {
		redisDao.put(entity);
	}

	public void delete(User entity) {
		redisDao.delete(entity);
	}

	public User get(User entity) {
		return redisDao.get(entity);
	}

	public List<User> findAll(User entity) {
		return redisDao.findAll(entity);
	}
}
