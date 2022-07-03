package com.neusoft.java.hars.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @author  Lily
 */
@Entity
@Table(name = "t_user")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4936218787926407419L;

	//用户名
	@Column(name = "user_name")
	private String userName;

	//显示名
	@Column(name = "name")
	private String name;

	//密码
	@Column(name = "password")
	private String password;

	//注册日期
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "registration_date")
	private Date registrationDate;

	//用户等级
	@Column(name = "user_level")
	private int userLevel;

	//创建时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date createTime;
}
