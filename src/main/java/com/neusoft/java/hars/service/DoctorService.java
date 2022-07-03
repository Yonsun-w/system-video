package com.neusoft.java.hars.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.java.hars.entity.Doctor;
import com.neusoft.java.hars.repository.DoctorDao;
import com.neusoft.java.hars.utils.DynamicSpecifications;
import com.neusoft.java.hars.utils.SearchFilter;

/**
 * DoctorService
 */
// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional(rollbackFor = Exception.class)
public class DoctorService extends EntityManager<Doctor, Long> {

	@Autowired
	private DoctorDao dao;

	@Override
	protected JpaRepository<Doctor, Long> getEntityDao() {
		return dao;
	}

	public Page<Doctor> getPage(int pageNumber, int pageSize, String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Page<Doctor> listPage = dao.findAll(pageRequest);
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

	/**
	 * 创建动态查询条件组合. 暂时 没有 添加
	 */
	private Specification<Doctor> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<Doctor> spec = DynamicSpecifications.bySearchFilter(filters.values(), Doctor.class);
		return spec;
	}

}
