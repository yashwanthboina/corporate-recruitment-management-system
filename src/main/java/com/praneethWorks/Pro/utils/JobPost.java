package com.praneethWorks.Pro.utils;

import java.util.List;

public class JobPost{
	private int pid;
	private String title;
	private String compName;
	private List<String> qualification;
	private String experence;
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
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public List<String> getQualification() {
		return qualification;
	}
	public void setQualification(List<String> qualification) {
		this.qualification = qualification;
	}
	public String getExperence() {
		return experence;
	}
	public void setExperence(String experence) {
		this.experence = experence;
	}
//	public void setTitle(String string) {
//		// TODO Auto-generated method stub
//		this.title=title;
//	}
}
