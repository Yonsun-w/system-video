package com.neusoft.java.hars.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @author  lwf
 */
@Entity
@Table(name = "t_visit_info")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class VisitInfo extends BaseEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8279040545030076313L;

	//出诊序号
	@Column(name = "visit_num")
	private int visitNum;

	//医生id
	@ManyToOne
	@JoinColumn(name = "doc_id", referencedColumnName = "id", nullable = false)
	private Doctor doctor;

	//出诊日期
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "visit_datetime")
	private Date visitDatetime;

	//出诊时段，单位分钟，如15表示每15分钟看一名患者
	@Column(name = "visit_period")
	private int visitPeriod;

	//最大人数
	@Column(name = "max_num")
	private int maxNum;

	//出诊状态：有号源、无号源
	@Column(name = "status")
	private int status;

	public Date getVisitPeriodDate() {
		//Date date = new Date(); //取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(this.visitDatetime);
		calendar.add(calendar.MINUTE, this.visitPeriod); //把日期往后增加一天,整数往后推,负数往前移动
		return calendar.getTime();
	}

}
