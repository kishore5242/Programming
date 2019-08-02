package com.klearn.hibernate.dao;

import org.springframework.stereotype.Repository;

import com.klearn.hibernate.model.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDao implements EmployeeDao {

	@Override
	public void saveEmployee(Employee employee) {
		persist(employee);
	}

}
