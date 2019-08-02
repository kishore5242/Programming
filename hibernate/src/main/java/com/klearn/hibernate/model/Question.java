package com.klearn.hibernate.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name="QUESTION")
public class Question {

	@Id
	@Column(name="Q_ID")
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Integer qId;
	
	@Column(name="Q_DESC")
	private String qDesc;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="Q_ID")
	@OrderColumn(name="POS")
	private List<Answer> answers = new ArrayList<Answer>();

	
	public Integer getqId() {
		return qId;
	}

	public void setqId(Integer qId) {
		this.qId = qId;
	}

	public String getqDesc() {
		return qDesc;
	}

	public void setqDesc(String qDesc) {
		this.qDesc = qDesc;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	
}
