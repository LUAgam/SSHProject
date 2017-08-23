/**

* Title: UserController.java

* Description: 

* Copyright: Copyright (c) 2017

* @author AMGuo

* @date 2017年8月8日

* @version 1.0

*/
package com.aomen.guo.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aomen.guo.entity.User;
import com.aomen.guo.service.impl.RedisService;
import com.aomen.guo.service.impl.UserServiceImpl;

/**
 * 
 * Title: UserController
 * 
 * Description:
 * 
 * @author AMGuo
 * 
 * @date 2017年8月8日
 * 
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserServiceImpl userService;

	@Autowired
	RedisService redisService;

	@RequestMapping(method = RequestMethod.GET)
	public String do_list(Model model) {
		logger.info("查询用户");
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "/user/list";
	}

	@RequestMapping(value = "/save", method = RequestMethod.PUT)
	public String do_save(@RequestParam("name") String name, Model model) {
		logger.info("查询用户：" + 1);
		User user = userService.get(1L);
		user.setName(name);
		userService.saveOrUpdate(user);
		// redis操作
		redisService.put(user);
		List<User> userList = redisService.findAll(user);
		for (User user3 : userList) {
			System.err.println("redis存储为：" + user3.getName());
		}
		model.addAttribute("user", user);

		return "/user/list";
	}
}
