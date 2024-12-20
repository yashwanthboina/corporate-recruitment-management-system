package com.praneethWorks.Pro.utils;

public class StanderdResponce {
	private int code;
	private String message;
	
	public StanderdResponce() {
		code=-1;
		message="Internal Server error";
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
