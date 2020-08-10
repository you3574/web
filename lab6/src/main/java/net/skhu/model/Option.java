package net.skhu.model;

public class Option {

	int value; //옵션 번호
	String label; //옵션 내용


	public Option(int value, String label) {
		this.value = value;
		this.label = label;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}



}
