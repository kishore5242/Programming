package com.kishore5242.tests.bean;

public class McqOption {

	private String option;
	private boolean correct;

	public McqOption(String option, boolean correct) {
		super();
		this.option = option;
		this.correct = correct;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

}
