package com.klearn.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ANSWER")
public class Answer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="A_ID")
	private Integer aId;
	
	@Column(name="A_DESC")
	private String aDesc;

	public String getaDesc() {
		return aDesc;
	}

	public void setaDesc(String aDesc) {
		this.aDesc = aDesc;
	}
	
}
