package com.neusoft.java.hars.repository;

import com.neusoft.java.hars.entity.User;

/**
 * 
 * @author Thinkpad
 *
 */
public interface UserDao extends BaseDao<User, Long> {

	/**根据userName 查询 user 数据*/
	User findByUserName(String userName);
	
	//User findById(long id);
	
	//User findByUserNameAndName(String userName, String name);
	
}
