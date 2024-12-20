package com.praneethWorks.Pro.utils;

import java.util.*;

public class AccountDetailsResponse extends StanderdResponce{
	
	private int uid;
	private String name;
	private List<String> skills;
	private List<AccountDetails_jobs> savedJobs;
	private List<AccountDetails_jobs> postedJobs;
	private boolean getUpates;
	
	public AccountDetailsResponse(){
		super();
		skills=new ArrayList<>();
		savedJobs=new ArrayList<>();
		postedJobs=new ArrayList<>();
	}
	
	public void addSkill(String skill) {
		skills.add(skill);
	}
	
	public void addSavedJob(AccountDetails_jobs obj) {
		savedJobs.add(obj);
	}
	
	public void addPostedJob(AccountDetails_jobs obj) {
		postedJobs.add(obj);
	}	
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	public List<AccountDetails_jobs> getSavedJobs() {
		return savedJobs;
	}
	public void setSavedJobs(List<AccountDetails_jobs> savedJobs) {
		this.savedJobs = savedJobs;
	}
	public List<AccountDetails_jobs> getPostedJobs() {
		return postedJobs;
	}
	public void setPostedJobs(List<AccountDetails_jobs> postedJobs) {
		this.postedJobs = postedJobs;
	}
	public boolean isGetUpates() {
		return getUpates;
	}
	public void setGetUpates(boolean getUpates) {
		this.getUpates = getUpates;
	}
	
	
	

}
