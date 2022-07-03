package com.neusoft.java.hars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.java.hars.entity.VisitInfo;
import com.neusoft.java.hars.repository.VisitInfoDao;

/**
 * VisitInfoService
 */
// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional(rollbackFor = Exception.class)
public class VisitInfoService extends EntityManager<VisitInfo, Long> {

	@Autowired
	private VisitInfoDao dao;

	@Override
	protected JpaRepository<VisitInfo, Long> getEntityDao() {
		return dao;
	}

	public Page<VisitInfo> getPage(int pageNumber, int pageSize, String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Page<VisitInfo> listPage = dao.findAll(pageRequest);
		return listPage;
	}

	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pageSize, String sortType) {
		Sort sort = null;
		//默认 id 排序 可以 按照 需求 调整
		if ("auto".equals(sortType)) {
			sort = Sort.by(Sort.Order.desc("id"));
		}
		return PageRequest.of(pageNumber - 1, pageSize, sort);
	}

}
