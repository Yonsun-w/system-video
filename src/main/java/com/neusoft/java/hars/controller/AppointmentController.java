package com.neusoft.java.hars.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.neusoft.java.hars.entity.Myyuyue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neusoft.java.hars.entity.Appointment;
import com.neusoft.java.hars.entity.User;
import com.neusoft.java.hars.entity.VisitInfo;
import com.neusoft.java.hars.service.AppointmentService;
import com.neusoft.java.hars.service.UserService;
import com.neusoft.java.hars.service.VisitInfoService;
import com.neusoft.java.hars.utils.Constant;

import io.swagger.annotations.ApiOperation;

/**
 *
 * 预约处理Controller
 */
@Controller
@RequestMapping(value = "/web/appointment")
public class AppointmentController {

	/**
	 * 预约处理Servicec注入
	 */
	@Autowired
	private AppointmentService appointmentService;

	/**
	 * 医生出诊Servicec注入
	 */
	@Autowired
	private VisitInfoService visitInfoService;

	/**
	 * 用户Servicec注入
	 */
	@Autowired
	private UserService userService;

	/**
	 * 发起预约
	 * @param id
	 * @param request
	 * @return
	 */
	@ResponseBody //返回 json 数据
	@ApiOperation(httpMethod = "POST", value = "创建", notes = "")
	@RequestMapping(value = "create/{id}", method = RequestMethod.POST)
	public Map<String, Object> create(@PathVariable("id") Long id, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			if (user == null) {
				map.put("code", "500");
				map.put("message", "没有用户信息");
				return map;
			}
			//不是查出来的 user 所以 必须 查一下
			user = userService.findByUserName(user.getUserName());
			VisitInfo visitInfo = visitInfoService.get(id);
			try {
				appointmentService.create(visitInfo, user);
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("code", "200");
			map.put("message", "预约成功");
			return map;
		} catch (Exception e) {
			map.put("code", "500");
			map.put("message", "预约失败");
			e.getMessage();
			return map;
		}
	}

	/**
	 * 我的预约列表——带分页查询
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @param request
	 * @param model
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
		Page<Appointment> page = appointmentService.getPage(pageNumber, pageSize, sortType);
		model.addAttribute("list", page);
		return "appointment/list";
	}





	@RequestMapping(value = "/wodeyuyue", method = RequestMethod.GET)
	public String wodeyuyue(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
							@RequestParam(value = "page.size", defaultValue = Constant.PAGE_SIZE) int pageSize,
							@RequestParam(value = "sortType", defaultValue = "auto") String sortType, HttpServletRequest request,
							Model model) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "login";
		}

		Myyuyue myyuyue1 = new Myyuyue();
		Myyuyue myyuyue2 = new Myyuyue();
		Myyuyue myyuyue3 = new Myyuyue();

		myyuyue1.setName("马tony").setType("私房照").setValue("5000.00");
		myyuyue2.setName("马tony").setType("婚纱招").setValue("4000.00");
		myyuyue3.setName("马tony").setType("运动照").setValue("6000.00");

		List list = new LinkedList();
		list.add(myyuyue1);
		list.add(myyuyue2);
		list.add(myyuyue3);


		model.addAttribute("list", list);
		return "appointment/mylist";
	}

}
