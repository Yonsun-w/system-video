package com.neusoft.java.hars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.java.hars.entity.Appointment;
import com.neusoft.java.hars.entity.User;
import com.neusoft.java.hars.entity.VisitInfo;
import com.neusoft.java.hars.repository.AppointmentDao;
import com.neusoft.java.hars.repository.VisitInfoDao;
import com.neusoft.java.hars.utils.Random;

/**
 * AppointmentService
 */
// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional(rollbackFor = Exception.class)
public class AppointmentService extends EntityManager<Appointment, Long> {

	@Autowired
	private AppointmentDao dao;

	@Autowired
	private VisitInfoDao visitInfoDao;

	@Override
	protected JpaRepository<Appointment, Long> getEntityDao() {
		return dao;
	}

	public void create(VisitInfo visitInfo, User user) {
		Appointment model = new Appointment();
		model.setApptNum(Random.createRandom(true, 6));
		model.setDoctor(visitInfo.getDoctor());
		model.setVisitInfo(visitInfo);
		model.setFee(visitInfo.getDoctor().getFee());
		model.setStatus(1);
		model.setUser(user);
		visitInfo.setStatus(2);
		//操作两张表 所以放在 service 里 处理  这样 可以 回滚
		visitInfoDao.save(visitInfo);
		dao.save(model);
	}

	public Page<Appointment> getPage(int pageNumber, int pageSize, String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Page<Appointment> listPage = dao.findAll(pageRequest);
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

}
