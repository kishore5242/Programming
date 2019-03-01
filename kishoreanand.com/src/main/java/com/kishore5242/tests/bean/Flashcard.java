package com.kishore5242.tests.bean;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="flashcard")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Flashcard implements Comparable<Flashcard>{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="stream")
	private String stream;
	
	@Column(name="front")
	private String front;
	
	@Column(name="back")
	private String back;
	
	@Column(name="position")
	private int position;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public String getFront() {
		return front;
	}
	public void setFront(String front) {
		this.front = front;
	}
	public String getBack() {
		return back;
	}
	public void setBack(String back) {
		this.back = back;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public int compareTo(Flashcard o) {
		return this.position - o.getPosition();
	}
	

}
