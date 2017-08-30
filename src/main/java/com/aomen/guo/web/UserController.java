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

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aomen.guo.entity.User;
import com.aomen.guo.service.impl.RedisService;
import com.aomen.guo.service.impl.UserServiceImpl;
import com.aomen.guo.util.ErrorUtils;
import com.aomen.guo.web.formbean.UserFB;

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
    public String do_save(@ModelAttribute @Valid UserFB userFB, BindingResult result, Model model, RedirectAttributes attributes) {
        logger.info("保存用户：" + userFB.getId());
        if (result.hasErrors()) {
            attributes.addFlashAttribute("message", ErrorUtils.fetchAllErrorMessages(result));
            return "redirect:/user";
        }
        User user = userService.toEntity(userFB);
        userService.save(user);
        return "redirect:/user";
    }
}
