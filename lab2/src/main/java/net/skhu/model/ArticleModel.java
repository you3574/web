package net.skhu.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;



public class ArticleModel {
    int id;

 	@NotEmpty(message="제목을 입력하세요")
    String subject;

    @Size(min=13, message="내용을 입력하세요")
    String message;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
