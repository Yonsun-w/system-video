package com.neusoft.java.hars.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.neusoft.java.hars.entity.Doctor;
import com.neusoft.java.hars.entity.User;
import com.neusoft.java.hars.service.DoctorService;
import com.neusoft.java.hars.utils.Constant;

import io.swagger.annotations.ApiOperation;

/**
 * 医生管理Controller
 *
 * @author auto
 */
@Controller
@RequestMapping(value = "/web/doctor")
public class DoctorController {

	/**
	 * 医生Servicec注入
	 */
	@Autowired
	private DoctorService doctorService;

	/**
	 * 医生列表
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

		Page<Doctor> page = doctorService.getPage(pageNumber, pageSize, sortType);
		model.addAttribute("list", page);
		model.addAttribute("date", getDay());
		return "doctor/list";
	}

	/**
	 * 获取后一天
	 * @return
	 */
	private String getDay() {
		// 取时间
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		// 把日期往后增加一天,整数  往后推,负数往前移动
		calendar.add(Calendar.DATE, 1);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = format.format(calendar.getTime());
		return dateString;
	}
}
