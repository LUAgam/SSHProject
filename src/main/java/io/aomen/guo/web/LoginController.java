package io.aomen.guo.web;

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

import io.aomen.guo.Constant;
import io.aomen.guo.entity.User;
import io.aomen.guo.service.UserService;
import io.aomen.guo.web.formbean.UserFB;

@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	UserService userService;

	/**
	 * 
	 * 
	 * Title: 登录界面
	 * 
	 * Description:
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String do_login() {
		return "/account/login";
	}

	/**
	 * 
	 * 
	 * Title: 登录
	 * 
	 * Description:
	 * 
	 * @param username
	 * @param password
	 * @param rememberMe
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String do_login(@ModelAttribute @Valid UserFB fb, BindingResult result, Model model) {
		if (result.hasErrors()) {
			LOGGER.warn(fetchAllErrorMessages(result));
			model.addAttribute(Constant.MESSAGE, fetchAllErrorMessages(result));
			model.addAttribute("user", fb);
			return "/account/login";
		}
		User user = userService.findByUsername(fb.getUsername());
		if (user == null) {
			// 账号为空
			LOGGER.warn(Constant.USER_EMPTY);
			model.addAttribute(Constant.MESSAGE, Constant.USER_EMPTY);
			model.addAttribute("user", fb);
			return "/account/login";
		} else if (!fb.getPassword().equals(user.getPassword())) {
			// 密码错误
			LOGGER.warn(Constant.PASSWORD_ERROR);
			model.addAttribute(Constant.MESSAGE, Constant.PASSWORD_ERROR);
			model.addAttribute("user", fb);
			return "/account/login";
		} else {
			// 登录成功
			LOGGER.info(user.getUsername() + Constant.LOGIN_SUCCESS);
			return "redirect:/index";
		}
	}

}
