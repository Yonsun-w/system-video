package com.neusoft.java.hars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.neusoft.java.hars.entity.Doctor;

/**
 * 
 * @author Thinkpad
 *
 */
public interface DoctorDao extends JpaRepository<Doctor, Long>, JpaSpecificationExecutor<Doctor> {

}
