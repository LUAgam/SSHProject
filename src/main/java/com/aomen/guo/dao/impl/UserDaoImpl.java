/**

* Title: UserDaoImpl.java

* Description: 

* Copyright: Copyright (c) 2017

* @author AMGuo

* @date 2017��8��7��

* @version 1.0

*/
package com.aomen.guo.dao.impl;

import org.springframework.stereotype.Repository;

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
@Repository(value = "userDao")
public class UserDaoImpl extends BaseDaoImpl<User, Long> {

}
