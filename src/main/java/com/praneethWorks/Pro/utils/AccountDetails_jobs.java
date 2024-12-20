package com.praneethWorks.Pro.utils;

public class AccountDetails_jobs {
	private int pid;
	private String title;
	
	public AccountDetails_jobs() {
		
	}
	
	public AccountDetails_jobs(int pid,String title) {
		this.pid=pid;
		this.title=title;
	}
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
