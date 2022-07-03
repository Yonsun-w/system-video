package com.neusoft.java.hars.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @author  lwf
 */
@Entity
@Table(name = "t_appointment")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Appointment extends BaseEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3847755628413416989L;

	//预约号
	@Column(name = "appt_num", length = 12)
	private String apptNum;

	//预约id
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "visit_id", referencedColumnName = "id")
	private VisitInfo visitInfo;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "doc_id", referencedColumnName = "id")
	private Doctor doctor;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	//费用
	@Column(name = "fee")
	private BigDecimal fee;

	//状态：已预约、已就诊、已取消、未就诊
	@Column(name = "status")
	private int status;
}
