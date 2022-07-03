package com.neusoft.java.hars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.java.hars.entity.User;
import com.neusoft.java.hars.repository.UserDao;

//Spring Bean的标识.
@Component
//类中所有public函数都纳入事务管理的标识.
@Transactional(rollbackFor = Exception.class)
public class UserService extends EntityManager<User, Long> {

	@Autowired
	private UserDao dao;
	
	public User findByUserName(String userName) {
		return dao.findByUserName(userName);
	}
	
	
	@Override
	protected JpaRepository<User, Long> getEntityDao() {
		return dao;
	}
	
}
