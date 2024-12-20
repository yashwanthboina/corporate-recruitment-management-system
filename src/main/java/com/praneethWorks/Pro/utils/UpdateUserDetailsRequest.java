package com.praneethWorks.Pro.utils;

import java.util.*;

public class UpdateUserDetailsRequest {
	private int uid;
	private List<String> skills;
	private boolean getUpates;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public boolean isGetUpates() {
		return getUpates;
	}
	public void setGetUpates(boolean getUpates) {
		this.getUpates = getUpates;
	}
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	
	
	
	
}
