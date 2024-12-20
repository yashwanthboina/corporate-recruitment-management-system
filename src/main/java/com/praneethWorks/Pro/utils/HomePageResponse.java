package com.praneethWorks.Pro.utils;

import java.util.*;

public class HomePageResponse extends StanderdResponce{

	private List<JobPost> posts;
	
	public HomePageResponse() {
		super();
		posts=new ArrayList<>();
	}

	public List<JobPost> getPosts() {
		return posts;
	}

	public void setPosts(List<JobPost> posts) {
		this.posts = posts;
	}
	
	public void addPost(JobPost p) {
		posts.add(p);
	}
	
	
}
