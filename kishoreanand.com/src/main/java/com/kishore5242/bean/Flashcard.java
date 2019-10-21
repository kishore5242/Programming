package com.kishore5242.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="flashcard")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Flashcard implements Comparable<Flashcard>{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "flashcard_id")
	private int flashcard_id;
	
	@Column(name="stream")
	private String stream;
	
	@Column(name="front")
	private String front;
	
	@Column(name="back", length = 10000)
	private String back;
	
	@Column(name="position")
	private int position;
	
	@Column(name="color")
	private String color;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="topic_id", nullable = false)
	private Topic topic;
	

	public int getFlashcard_id() {
		return flashcard_id;
	}
	public void setFlashcard_id(int flashcard_id) {
		this.flashcard_id = flashcard_id;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
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

	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public int compareTo(Flashcard o) {
		return this.position - o.getPosition();
	}
	

}
