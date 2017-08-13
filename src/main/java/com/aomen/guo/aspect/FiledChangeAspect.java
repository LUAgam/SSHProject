/**

* Title: FiledChangeAspect.java

* Description: 

* Copyright: Copyright (c) 2017

* @author AMGuo

* @date 2017年8月9日

* @version 1.0

*/
package com.aomen.guo.aspect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.aomen.guo.dao.impl.GeneralDaoImpl;

/**
 * 
 * Title: FiledChangeAspect
 * 
 * Description:
 * 
 * @author AMGuo
 * 
 * @date 2017年8月9日
 * 
 */
@Aspect
@Component
@Order(1)
public class FiledChangeAspect {

	@Autowired
	GeneralDaoImpl generalDao;

	private static final Logger logger = Logger.getLogger(FiledChangeAspect.class);

	@Pointcut("execution(public * com.aomen.guo.service.impl..saveOrUpdate(..))")
	public void performance() {
	}

	@Before("performance() && args(entity)")
	public void before(JoinPoint jp, Object entity) throws Exception {
		System.err.println("[before]entity");
		Object target = jp.getTarget();
		Long id = (Long) entity.getClass().getMethod("getId", new Class[] {}).invoke(entity, new Class[] {});
		Object entity1 = generalDao.findOne(entity, id);
		Field[] declaredFields = entity1.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			if ((field.getModifiers() & Modifier.STATIC) != Modifier.STATIC) {
				String fieldName = field.getName();
				String firstLetter = fieldName.substring(0, 1).toUpperCase();
				String getMethodName = "get" + firstLetter + fieldName.substring(1);
				Method getMethod = entity1.getClass().getMethod(getMethodName, new Class[] {});
				Object z = getMethod.invoke(entity1, new Object[] {});
				System.err.println(z);
			}
		}
		logger.info("[before]entity");

	}

	@AfterReturning("performance() && args(entity)")
	public void after(Object entity) throws Exception {
		System.err.println("[After]entity");
		Field[] declaredFields = entity.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			if ((field.getModifiers() & Modifier.STATIC) != Modifier.STATIC) {
				String fieldName = field.getName();
				String firstLetter = fieldName.substring(0, 1).toUpperCase();
				String getMethodName = "get" + firstLetter + fieldName.substring(1);
				Method getMethod = entity.getClass().getMethod(getMethodName, new Class[] {});
				Object z = getMethod.invoke(entity, new Object[] {});
				System.err.println(z);
			}
		}
		logger.info("[after]entity");
	}

}
