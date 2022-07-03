package com.neusoft.java.hars.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @author  lwf
 */
@Entity
@Table(name = "t_department")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Department extends BaseEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 5329725842250394106L;

	//代码
	@Column(name = "code", length = 8)
	private String code;

	
	//科室代码
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hospital_code", referencedColumnName = "code", nullable = false)
	private Hospital hospital;

	//科室名称
	@Column(name = "name", length = 100)
	private String name;

	//简介
	@Column(name = "brief")
	private String brief;
}
