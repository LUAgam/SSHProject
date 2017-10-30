/**

* Title: RecordLogUtil.java

* Description: 

* Copyright: Copyright (c) 2017

* @author AMGuo

* @date 2017年8月10日

* @version 1.0

*/
package io.aomen.guo.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.List;

/**
 * 
 * Title: RecordLogUtil
 * 
 * Description:
 * 
 * @author AMGuo
 * 
 * @date 2017年8月10日
 * 
 */
public class RecordLogUtil {

	/**
	 * 
	 * 通过实现类包名得到实体对象
	 * 
	 * @param classUrl
	 * @return
	 */
	public static Object getEntityObject(String classUrl) {
		Object obj = null;
		try {
			Class<?> classType = Class.forName(classUrl);
			Method method = classType.getMethod("getEntityClass");
			obj = method.invoke(classType.newInstance());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * 通过ID得到原始数据
	 * 
	 * @param target
	 * @param ID
	 * @return
	 */
	public static Object getObject(Object target, Object ID) {
		Object obj = null;
		try {
			Class<?> classType = target.getClass();

			Method findById = classType.getMethod("findById", new Class<?>[] { Serializable.class });
			obj = findById.invoke(target, new Object[] { ID });

			// Method sessionClear = classType.getMethod("sessionClear");
			// sessionClear.invoke(target);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public Object getObject(Object target, Object entityObject, String hql, Object[] ids) {
		Object obj = null;
		StringBuffer splitJoint = new StringBuffer();
		splitJoint.append("from ");
		String entityStr = entityObject.toString();
		splitJoint.append(entityStr.substring(entityStr.lastIndexOf(".") + 1, entityStr.length()) + " ");
		splitJoint.append(hql.substring(hql.indexOf("where"), hql.length()).trim());
		try {
			Class<?> fieldType = Object[].class;
			Class<?> classType = target.getClass();
			Method findByHql = classType.getMethod("findByHql", new Class<?>[] { String.class, fieldType });
			List list = (List) findByHql.invoke(target, new Object[] { splitJoint.toString(), ids });
			obj = list.get(0);

			Method sessionClear = classType.getMethod("sessionClear");
			sessionClear.invoke(target);

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * 返回参数里面是否有当前操作实现类的实体对象
	 * 
	 * @param parames
	 * @param entityObject
	 * @return
	 */
	public static Object parseParames(Object[] parames, Object entityObject) {
		Object obj = null;
		for (int i = 0; i < parames.length; i++) {
			if (parames[i].getClass().toString().equals(entityObject.toString())) {
				obj = parames[i];
				return obj;
			}
		}
		return obj;
	}

	/**
	 * 得到对象ID
	 * 
	 * @param newObject
	 * @return
	 */
	public static String getFieldIdValue(Object newObject) {
		String result = "";
		Field[] fields = newObject.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(javax.persistence.Id.class)) {

				result = getFieldValue(newObject, field.getName()).toString();

				return result;
			}
		}
		return result;
	}

	/**
	 * 
	 * 得到当对象字段的值
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Object getFieldValue(Object obj, String fieldName) {

		Object filedValue = null;
		// 获得字段第一个字母大写
		String firstLetter = fieldName.substring(0, 1).toUpperCase();
		// 转换成字段的get方法
		String getMethodName = "get" + firstLetter + fieldName.substring(1);
		// 是否存在此方法
		try {
			Method fieldMethod = obj.getClass().getMethod(getMethodName);
			Object objValue = fieldMethod.invoke(obj);
			if (objValue instanceof Integer || objValue instanceof String || objValue instanceof Long
					|| objValue instanceof Float || objValue instanceof Double || objValue instanceof Timestamp) {
				filedValue = objValue;
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return filedValue;
	}

	public static Object getIdFiled(Object target) throws Exception {
		Class<?> classType = target.getClass();
		Method findById = classType.getMethod("getId", new Class<?>[] { Serializable.class });
		return findById.invoke(target, new Object[] {});

	}

}
