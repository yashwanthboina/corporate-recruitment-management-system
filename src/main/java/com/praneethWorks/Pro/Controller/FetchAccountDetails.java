package com.praneethWorks.Pro.Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import com.praneethWorks.Pro.JDBC_Connection.Connect;
import com.praneethWorks.Pro.utils.AccountDetailsResponse;
import com.praneethWorks.Pro.utils.AccountDetails_jobs;
import com.praneethWorks.Pro.utils.Hashing;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.*;
import com.google.gson.*;

public class FetchAccountDetails extends HttpServlet{
	
	private String mail;
	
	private AccountDetailsResponse responce;
	
	private Connect con;
	
	
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String token=req.getParameter("token");
		mail=Hashing.doRevHashing(token);
		responce=new AccountDetailsResponse();
		try {
			con=new Connect();
			setAccountDetails();
			con.closeCon();
			res.getWriter().print(new Gson().toJson(responce));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void setAccountDetails() throws SQLException {
		ResultSet result=con.getData("select uid,name,skills,getUpdadtes,savedJobs from users where email='"+mail+"'"),set=null;
		if(result.next()) {
			int uid=result.getInt("uid");
			String str="";
			responce.setCode(1);
			responce.setMessage("Account details.");
			responce.setName(result.getString("name"));
			responce.setUid(uid);
			str=result.getString("skills");
			if(str!=null) {
				responce.setSkills(Arrays.asList(str.split("\\^")));
			}
			responce.setGetUpates(result.getBoolean("getUpdadtes"));
			
			str=result.getString("savedJobs");
			
			if(str!=null) {
				List<String> saved=Arrays.asList(str.split("\\^"));
				
				for(String s:saved) {
					try {
						int pid=Integer.parseInt(s);
						set=con.getData("select title from job_posts where pid="+pid);
						responce.addSavedJob(new AccountDetails_jobs(pid,set.getString("title")));
					}catch(Exception e) {
						
					}
				}
			}
			
			set=con.getData("select pid,title from job_posts where uid="+uid);
			while(set.next()) {
				responce.addPostedJob(new AccountDetails_jobs(set.getInt("pid"),set.getString("title")));
			}
		}else {
			responce.setCode(0);
			responce.setMessage("Account not found.");
		}
	}

}
