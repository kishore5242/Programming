package com.kishore5242.tests.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="stream")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Stream {
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "stream_Sequence")
    @SequenceGenerator(name = "stream_Sequence", sequenceName = "STREAM_SEQ")
	private Integer stream_id;
	
	@Column(name = "stream_name")
	private String stream_name;
	
	@OneToMany(cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER,
            mappedBy="stream")
	private Set<Topic> topics;
	
	@Column(name = "username")
	private String username;
	
	public Integer getStream_id() {
		return stream_id;
	}
	public void setStream_id(Integer stream_id) {
		this.stream_id = stream_id;
	}
	public String getStream_name() {
		return stream_name;
	}
	public void setStream_name(String stream_name) {
		this.stream_name = stream_name;
	}
	public Set<Topic> getTopics() {
		return topics;
	}
	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
