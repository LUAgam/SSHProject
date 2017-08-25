package com.aomen.guo.webservice;

import com.aomen.guo.entity.User;

/**
 * @author AMGuo E-mail:www.guoao@foxmail.com
 * @version 创建时间：2017年8月25日 下午12:44:40 类说明
 */
public class BaseServiceImpl implements BaseService {

    @Override
    public String sayHello(String name) {
        return "Hello" + name;
    }

    public User getUser(User user) {
        return user;
    }

}
