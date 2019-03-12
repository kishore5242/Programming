package com.kishore5242.tests.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="topic")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Topic implements Comparable<Topic>{
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "topic_Sequence")
    @SequenceGenerator(name = "topic_Sequence", sequenceName = "TOPIC_SEQ")
	private Integer topic_id;
	
	@Column(name = "topic_name")
	private String topic_name;
	
	@Column(name = "pos")
	private Integer pos;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="stream_id", nullable = false)
	private Stream stream;
	
	@OneToMany(cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER,
            mappedBy="topic")
	private Set<Flashcard> flashcards;
	
	public Topic(){};
	
	
	
	public Integer getTopic_id() {
		return topic_id;
	}

	public void setTopic_id(Integer topic_id) {
		this.topic_id = topic_id;
	}

	public String getTopic_name() {
		return topic_name;
	}

	public void setTopic_name(String topic_name) {
		this.topic_name = topic_name;
	}

	public Stream getStream() {
		return stream;
	}

	public void setStream(Stream stream) {
		this.stream = stream;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public Set<Flashcard> getFlashcards() {
		return flashcards;
	}

	public void setFlashcards(Set<Flashcard> flashcards) {
		this.flashcards = flashcards;
	}

	@Override
	public int compareTo(Topic o) {
		return this.pos - o.getPos();
	}
}
