package com.praneethWorks.Pro.utils;

import java.util.*;

public class JobResponse extends StanderdResponce{
	private int pid;
	private String title;
	private String company;
	private String location;
	private List<String> qualification;
	private String experence;
	private List<String> skills;
	private String description;
	private List<String> responsibilities;
	private String contact;
	private String date;
	
	public JobResponse() {
		super();
		qualification=new ArrayList<>();
		skills=new ArrayList<>();
		responsibilities=new ArrayList<>();
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
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<String> getQualification() {
		return qualification;
	}
	public void setQualification(List<String> qualification) {
		this.qualification = qualification;
	}
	public void addQualification(String Qualification) {
		qualification.add(Qualification);
	}
	
	public String getExperence() {
		return experence;
	}
	public void setExperence(String experence) {
		this.experence = experence;
	}
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	
	public void addSkill(String skill) {
		skills.add(skill);
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getResponsibilities() {
		return responsibilities;
	}
	
	public void addResponsibilitie(String responsibilitie) {
		responsibilities.add(responsibilitie);
	}
	
	public void setResponsibilities(List<String> responsibilities) {
		this.responsibilities = responsibilities;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
