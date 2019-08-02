package com.klearn.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.klearn.hibernate.dao.EmployeeDao;
import com.klearn.hibernate.model.Employee;

@Service("employeeService")
@Transactional
public class EmployeeServiveImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;
	
	@Override
	public void saveEmployee(Employee employee) {
		employeeDao.saveEmployee(employee);
	}

}
