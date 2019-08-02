package com.klearn.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE_PERMANENT")
@PrimaryKeyJoinColumn(name="ID")
public class PermEmp extends Employee {
	
	@Column(name="SALARY")
	private String salary;

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

}
