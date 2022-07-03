package com.neusoft.java.hars.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.neusoft.java.hars.entity.User;
import com.neusoft.java.hars.service.UserService;

import io.swagger.annotations.ApiOperation;

/**
 * 用户controller
 *
 *
 */
@Controller
@RequestMapping(value = "/web/user")
public class UserController {

	/**
	 * 用户Servicec注入
	 */
	@Autowired
	private UserService userService;

	/**
	 * 注册方法
	 * @param model
	 * @return
	 */
	@ApiOperation(httpMethod = "POST", value = "注册")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(User model) {
		//密码是需要加密的，本次在此先省略
		model.setCreateTime(new Date());
		model.setRegistrationDate(new Date());
		//业务逻辑在这补充，比如判断账号是否存在
		userService.save(model);
		//注册成功后，重定向到对应方法，这里又访问了登录的路由
		return "redirect:/web/user/login";
	}

	/**
	 * 跳转登录页面，没有做拦截器,开发时暂时不需要加
	 * @param
	 * @return
	 */
	@ApiOperation(httpMethod = "GET", value = "路由跳转登录页面")
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {

		return "login";


	}

	/**
	 * 登录接口
	 * @param model
	 * @return
	 */
	@ApiOperation(httpMethod = "POST", value = "登录接口")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPOST(User entity, Model model, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		User user = userService.findByUserName(entity.getUserName());
		if (user == null) {
			model.addAttribute("error", "用户名或密码错误");
			return "login";
		}
		if (!user.getPassword().equals(entity.getPassword())) {
			model.addAttribute("error", "用户名或密码错误");
			return "login";
		}
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		//重定向 传一个值
		return "redirect:/web/visitInfo/page";
	}

	/**
	 * 跳转注册页面
	 * @param model
	 * @return
	 */
	@ApiOperation(httpMethod = "GET", value = "注册页面")
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("action", "create");
		return "register";
	}
}
