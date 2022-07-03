package com.neusoft.java.hars.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @author  lwf
 */
@Entity
@Table(name = "t_doctor")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Doctor extends BaseEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1548844267707998079L;

	//科室代码
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_code", referencedColumnName = "code", nullable = false)
	private Department department;

	//姓名
	@Column(name = "name", length = 20)
	private String name;

	//职称
	@Column(name = "title", length = 100)
	private String title;

	//挂号费
	@Column(name = "fee")
	private BigDecimal fee;

	//简介
	@Column(name = "brief")
	private String brief;

	//CascadeType.REMOVE
	//Cascade remove operation，级联删除操作。
	//删除当前实体时，与它有映射关系的实体也会跟着被删除。
	//	CascadeType.MERGE
	//	Cascade merge operation，级联更新（合并）操作。
	//	当Student中的数据改变，会相应地更新Course中的数据。
	//	CascadeType.DETACH
	//	Cascade detach operation，级联脱管/游离操作。
	//	如果你要删除一个实体，但是它有外键无法删除，你就需要这个级联权限了。它会撤销所有相关的外键关联。
	//	CascadeType.REFRESH
	//	Cascade refresh operation，级联刷新操作。
	//	假设场景 有一个订单,订单里面关联了许多商品,这个订单可以被很多人操作,那么这个时候A对此订单和关联的商品进行了修改,与此同时,B也进行了相同的操作,但是B先一步比A保存了数据,那么当A保存数据的时候,就需要先刷新订单信息及关联的商品信息后,再将订单及商品保存。(来自良心会痛的评论)
	//	CascadeType.ALL
	//	Cascade all operations，清晰明确，拥有以上所有级联操作权限。

	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<VisitInfo> visitInfos = new ArrayList<>();
}
