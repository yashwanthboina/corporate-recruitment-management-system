package com.praneethWorks.Pro.Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.*;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.praneethWorks.Pro.JDBC_Connection.*;
import com.praneethWorks.Pro.utils.*;
import com.google.gson.*;

public class HomePageController extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) {
		try {
			res.getWriter().print(data());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String data() throws Exception{
		HomePageResponse response=new HomePageResponse();
		Connect con=new Connect();
		ResultSet result=con.getData("select pid,title,company,qualification,experence from job_posts where isActive=true");
		//con.closeCon();
		while(result.next()) {
			JobPost job=new JobPost();
			job.setPid(result.getInt("pid"));
			job.setTitle(result.getString("title"));
			job.setCompName(result.getString("company"));
			List<String> qualifi=Arrays.asList(result.getString("qualification").split("\\^"));
			job.setQualification(qualifi);
			job.setExperence(result.getString("experence"));
			response.addPost(job);
		}
//		JobPost job1=new JobPost();
//		job1.setPid(12);
//		job1.setTitle("Prate");
//		response.addPost(job1);
//		
//		JobPost job2=new JobPost();
//		job2.setPid(12);
//		job2.setTitle("Prate");
//		response.addPost(job2);
//		
		response.setCode(1);
		response.setMessage("Dada fetched successfully.");
		con.closeCon();
		return new Gson().toJson(response);
	}
}
