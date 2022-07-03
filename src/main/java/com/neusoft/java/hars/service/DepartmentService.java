package com.neusoft.java.hars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.java.hars.entity.Department;
import com.neusoft.java.hars.repository.DepartmentDao;

/**
 * DepartmentService
 */
// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional(rollbackFor = Exception.class)
public class DepartmentService extends EntityManager<Department, Long> {

	@Autowired
	private DepartmentDao dao;

	@Override
	protected JpaRepository<Department, Long> getEntityDao() {
		return dao;
	}

}
