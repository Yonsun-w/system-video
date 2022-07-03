package com.neusoft.java.hars.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.neusoft.java.hars.entity.User;
import com.neusoft.java.hars.entity.VisitInfo;
import com.neusoft.java.hars.service.VisitInfoService;
import com.neusoft.java.hars.utils.Constant;

import io.swagger.annotations.ApiOperation;

/**
 * 出诊信息Controller
 */
@Controller
@RequestMapping(value = "/web/visitInfo")
public class VisitInfoController {

	/**
	 * 出诊信息Service
	 */
	@Autowired
	private VisitInfoService visitInfoService;

	/**
	 * 列表查询
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

		Page<VisitInfo> page = visitInfoService.getPage(pageNumber, pageSize, sortType);
		model.addAttribute("list", page);
		return "visitInfo/list";
	}
}
