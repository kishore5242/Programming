package com.klearn.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE_CONTRACT")
@PrimaryKeyJoinColumn(name="ID")
public class ContractEmp extends Employee{

	@Column(name="PAY_PER_HOUR")
	private String payPerHour;

	public String getPayPerHour() {
		return payPerHour;
	}

	public void setPayPerHour(String payPerHour) {
		this.payPerHour = payPerHour;
	}
	
}
