/**

* Title: UserDaoImpl.java

* Description: 

* Copyright: Copyright (c) 2017

* @author AMGuo

* @date 2017��8��7��

* @version 1.0

*/
package com.aomen.guo.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.aomen.guo.entity.User;

/**
 * 
 * Title: UserDaoImpl
 * 
 * Description:
 * 
 * @author AMGuo
 * 
 * @date 2017��8��7��
 * 
 */
public interface UserDao extends BaseRepository<User, Long>, JpaSpecificationExecutor<Long> {

}
