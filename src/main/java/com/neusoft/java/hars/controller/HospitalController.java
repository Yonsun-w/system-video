package com.neusoft.java.hars.controller;

//import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.neusoft.java.hars.entity.videoguanli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.neusoft.java.hars.entity.Hospital;
import com.neusoft.java.hars.entity.User;
import com.neusoft.java.hars.service.HospitalService;
import com.neusoft.java.hars.utils.Constant;
import com.neusoft.java.hars.utils.Servlets;

import io.swagger.annotations.ApiOperation;
@Controller
@RequestMapping(value = "/web/hospital")
public class HospitalController {
	
	@Autowired
	private HospitalService hospitalService;
	
	/**
	 * 医院
	 * @return
	 */
	@ApiOperation(value = "分页", notes = "")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public String page(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = Constant.PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, HttpServletRequest request,
			Model model) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "login";
		}
		//为我们 封装了  动态 查询语句 search_这个 开头  search_EQ_xxxx(字段名称) 就是查询 相等
		//search_Like_xxxx  模糊
		//EQ, LIKE, GT, LT, GTE, LTE, NOTEQ, ISNULL, ISNOTNULL, ORLIKE, IN, NOTIN
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Page<Hospital> page = hospitalService.getPage(searchParams,pageNumber, pageSize, sortType);
		model.addAttribute("list", page);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "hospital/list";
	}



	@ApiOperation(value = "分页", notes = "")
	@RequestMapping(value = "/test1", method = RequestMethod.GET)
	public String test1(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
					   @RequestParam(value = "page.size", defaultValue = Constant.PAGE_SIZE) int pageSize,
					   @RequestParam(value = "sortType", defaultValue = "auto") String sortType, HttpServletRequest request,
					   Model model) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "login";
		}
		List<videoguanli> list = new LinkedList<>();
		videoguanli  v1 = new videoguanli();
		videoguanli  v2 = new videoguanli();
		videoguanli  v3 = new videoguanli();
		v1.setUser("admin").setVideo("马Tonye").setIsdone("未拍摄").setName("梦中-私房照");
		v2.setUser("admin").setVideo("马Tonye").setIsdone("未拍摄").setName("成人之美-婚纱照");
		v3.setUser("admin").setVideo("马Tonye").setIsdone("已拍摄").setName("坚毅-运动照");
		list.add(v1);
		list.add(v2);
		list.add(v3);
		model.addAttribute("list", list);
		return "hospital/mylist";
	}


	/**
	 * 跳转登录页面，没有做拦截器  开发时暂时不需要加
	 * @param
	 * @return
	 */
	@ApiOperation(httpMethod = "GET", value = "路由跳转登录页面")
	@RequestMapping(value = "/input", method = RequestMethod.GET)
	public String input(Model model) {
		model.addAttribute("action", "create");
		return "/hospital/input";
	}
	
	@ApiOperation(httpMethod = "GET", value = "路由跳转登录页面")
	@RequestMapping(value = "/input/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id,Model model) {
		Hospital hospital = hospitalService.get(id);
		model.addAttribute("model", hospital);
		model.addAttribute("action", "update");
		return "/hospital/input";
	}
	
	@ApiOperation(httpMethod = "GET", value = "详情页面")
	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id,Model model) {
		Hospital hospital = hospitalService.get(id);
		model.addAttribute("model", hospital);
		return "/hospital/show";
	}
	
	/**
	 * 注册方法
	 * @param model
	 * @return
	 */
	@ApiOperation(httpMethod = "POST", value = "创建")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(Hospital model) {
		//业务逻辑在这补  比如判断账号 是否存在
		hospitalService.save(model);
		//注册成功后 这要重定向到哪个方法 这里 又访问了 登录的路由
		return "redirect:/web/hospital/page";
	}
	
	@ApiOperation(httpMethod = "POST", value = "创建")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Hospital model) {
		//业务逻辑在这补  比如判断账号 是否存在
		hospitalService.save(model);
		//注册成功后 这要重定向到哪个方法 这里 又访问了 登录的路由
		return "redirect:/web/hospital/page";
	}
	
	@ApiOperation(httpMethod = "GET", value = "删除")
	@RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
	public String del(@PathVariable("id") Long id,Model model) {
		Hospital hospital = hospitalService.get(id);
		hospitalService.delete(hospital);
		return "redirect:/web/hospital/page";
	}
	
	@ApiOperation(httpMethod = "GET", value = "路由跳转登录页面")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Hospital> list = hospitalService.getAll();
		model.addAttribute("list", list);
		return "/hospital/list";
	}

}
