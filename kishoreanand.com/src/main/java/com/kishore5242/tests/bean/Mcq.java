package com.kishore5242.tests.bean;

import java.util.List;

public class Mcq {

	private String id;
	private String mcqQuestion;
	private List<McqOption> mcqOptions;
	private String explanation;

	public Mcq(String id, String mcqQuestion, List<McqOption> mcqOptions) {
		super();
		this.id = id;
		this.mcqQuestion = mcqQuestion;
		this.mcqOptions = mcqOptions;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMcqQuestion() {
		return mcqQuestion;
	}

	public void setMcqQuestion(String mcqQuestion) {
		this.mcqQuestion = mcqQuestion;
	}

	public List<McqOption> getMcqOptions() {
		return mcqOptions;
	}

	public void setMcqOptions(List<McqOption> mcqOptions) {
		this.mcqOptions = mcqOptions;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

}
