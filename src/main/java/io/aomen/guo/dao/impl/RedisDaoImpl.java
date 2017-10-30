/**

* Title: RedisDaoImpl.java

* Description: 

* Copyright: Copyright (c) 2017

* @author AMGuo

* @date 2017年8月13日

* @version 1.0

*/
package io.aomen.guo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import io.aomen.guo.dao.RedisDao;
import io.aomen.guo.entity.User;

/**
 * 
 * Title: RedisDaoImpl
 * 
 * Description:
 * 
 * @author AMGuo
 * 
 * @date 2017年8月13日
 * 
 */
@Repository("redisDao")
public class RedisDaoImpl implements RedisDao<User, Long> {

	@Autowired
	RedisTemplate<String, User> redisTemplate;

	@Override
	public void put(User entity) {
		redisTemplate.opsForList().rightPush(entity.getClass().getSimpleName(), entity);
	}

	@Override
	public void delete(User entity) {
		redisTemplate.opsForList().remove(entity.getClass().getSimpleName(), 1, entity);
	}

	@Override
	public User get(User entity) {
		return redisTemplate.opsForList().index(entity.getClass().getSimpleName(), 1);
	}

	@Override
	public List<User> findAll(User entity) {
		return redisTemplate.opsForList().range(entity.getClass().getSimpleName(), 0, 10000);
	}

}
