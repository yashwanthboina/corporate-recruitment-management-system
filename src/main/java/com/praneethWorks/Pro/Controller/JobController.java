package com.praneethWorks.Pro.Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import com.praneethWorks.Pro.JDBC_Connection.Connect;
import com.praneethWorks.Pro.utils.JobResponse;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.*;

public class JobController extends HttpServlet{
	
	private int pid;
	private JobResponse response=null;
	private Connect con=null;
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		pid=Integer.parseInt(req.getParameter("pid"));
		response=new JobResponse();
		try {
			infoJob();
			con.closeCon();
			res.getWriter().print(new Gson().toJson(response));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void infoJob() throws ClassNotFoundException, SQLException {
		con=new Connect();
		ResultSet result=con.getData("select * from job_posts where pid="+pid);
		
		if(result.next()) {
			response.setPid(pid);
			response.setTitle(result.getString("title"));
			response.setCompany(result.getString("company"));
			response.setLocation(result.getString("location"));
			response.setQualification(Arrays.asList(result.getString("qualification").split("\\^")));
			response.setExperence(result.getString("experence"));
			response.setSkills(Arrays.asList(result.getString("skills").split("\\^")));
			response.setDescription(result.getString("description"));
			response.setContact(result.getString("contact"));
			response.setDate(result.getString("postedDate"));
			Scanner s=new Scanner(result.getString("responsibilities"));
			
			while(s.hasNextLine()) {
				response.addResponsibilitie(s.nextLine());
			}
			
			response.setCode(1);
			response.setMessage("Data fetched Successfully.");
			
		}else {
			response.setCode(0);
			response.setMessage("Unable to fetch data.");
		}
		
	}

}
