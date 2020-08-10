package net.skhu.model;

import javax.validation.constraints.Size;

public class AnswerModel {

	int id;
	@Size(min=13, message="내용을 입력하세요")
	String message;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}



}
