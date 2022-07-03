package com.neusoft.java.hars.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @author  lwf
 */
@Entity
@Table(name = "t_hospital")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Hospital extends BaseEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8165248754364345263L;

	//代码
	@Column(name = "code", length = 18)
	private String code;

	//医院名称
	@Column(name = "name", length = 100)
	private String name;

	//医院等级
	@Column(name = "level")
	private int level;

	//地址
	@Column(name = "address", length = 100)
	private String address;

	//简介
	@Column(name = "brief")
	private String brief;
}
