/**

* Title: UserController.java

* Description: 

* Copyright: Copyright (c) 2017

* @author AMGuo

* @date 2017年8月8日

* @version 1.0

*/
package io.aomen.guo.web;

import java.util.List;

import javax.servlet.http.HttpUtils;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.aomen.guo.entity.User;
import io.aomen.guo.service.RedisService;
import io.aomen.guo.service.UserService;
import io.aomen.guo.util.ErrorUtils;
import io.aomen.guo.web.formbean.UserFB;

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
	UserService userService;

	@Autowired
	RedisService redisService;

	@RequestMapping(method = RequestMethod.GET)
	public String do_list(Model model) {
		logger.info("查询用户");
		Pageable pageable = new PageRequest(1, 3, new Sort(Sort.Direction.ASC, "id"));
		Page<User> users = userService.findPage(pageable);
		Page<UserFB> userFBs = userService.toPageFb(users);
		model.addAttribute("users", users);
		model.addAttribute("userFBs", userFBs);
		return "/user/list";
	}

	@ResponseBody
	@RequestMapping(value = "/getTableData", method = RequestMethod.GET)
	public MyPage getTableData(@RequestParam(value = "pageNumber", required = false) int pageNumber,
			@RequestParam(value = "pageSize", required = false) int pageSize,
			@RequestParam(value = "sortName", required = false) String sortName,
			@RequestParam(value = "sortOrder", required = false) String sortOrder) {
		Pageable pageable = new PageRequest(pageNumber, pageSize,
				new Sort(
						StringUtils.isEmpty(sortOrder) ? Sort.Direction.ASC
								: sortOrder.equalsIgnoreCase(Sort.Direction.ASC.toString()) ? Sort.Direction.ASC : Sort.Direction.DESC,
						StringUtils.isEmpty(sortName) ? "id" : sortName));
		Page<User> users = userService.findPage(pageable);
		Page<UserFB> userFBs = userService.toPageFb(users);
		MyPage myPage = new MyPage(userFBs.getTotalElements(), userFBs.getContent());
		return myPage;
	}

	class MyPage {
		private long total;

		private List<UserFB> rows;

		public MyPage(long total, List<UserFB> rows) {
			super();
			this.total = total;
			this.rows = rows;
		}

		/**
		 * @return the total
		 */
		public long getTotal() {
			return total;
		}

		/**
		 * @param total
		 *            the total to set
		 */
		public void setTotal(long total) {
			this.total = total;
		}

		/**
		 * @return the rows
		 */
		public List<UserFB> getRows() {
			return rows;
		}

		/**
		 * @param rows
		 *            the rows to set
		 */
		public void setRows(List<UserFB> rows) {
			this.rows = rows;
		}

	}

	@RequestMapping(value = "/save", method = RequestMethod.PUT)
	public String do_save(@ModelAttribute @Valid UserFB userFB, BindingResult result, Model model,
			RedirectAttributes attributes) {
		logger.info("保存用户：" + userFB.getId());
		if (result.hasErrors()) {
			attributes.addFlashAttribute("message", ErrorUtils.fetchAllErrorMessages(result));
			return "redirect:/user";
		}
		User user = userService.toEntity(userFB);
		User user2 = userService.save(user);
		/* redisService.put(user2); */
		return "redirect:/user";
	}
}
