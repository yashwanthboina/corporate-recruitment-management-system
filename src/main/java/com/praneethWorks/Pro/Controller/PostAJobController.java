package com.praneethWorks.Pro.Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.*;
import com.praneethWorks.Pro.JDBC_Connection.Connect;
import com.praneethWorks.Pro.JavaMail.SendMail;
import com.praneethWorks.Pro.utils.Hashing;
import com.praneethWorks.Pro.utils.PostAJobRequest;
import com.praneethWorks.Pro.utils.StanderdResponce;

public class PostAJobController extends HttpServlet{
	
	private PostAJobRequest poRequest=null;
	
	private int uid;
	
	private Connect con=null;
	
	private StanderdResponce responce=null;
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = req.getReader().readLine()) != null) {
            requestBody.append(line);
        }
        
        System.out.println(requestBody.toString());
        Gson gson=new Gson();
        poRequest=gson.fromJson(requestBody.toString(),PostAJobRequest.class);
        responce=new StanderdResponce();
        
        try {
			con=new Connect();
			if(getUid(Hashing.doRevHashing(poRequest.getToken()))) {
				if(setdata()>=1) {
					responce.setCode(1);
					responce.setMessage("Job Posted.");
					
					sendUpdatesToUser();
				}else {
					responce.setCode(0);
					responce.setMessage("Problem will posting.");
				}
			}
			
			con.closeCon();
			
			res.getWriter().print(gson.toJson(responce));
			
		} catch (ClassNotFoundException | SQLException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	private boolean getUid(String email) throws SQLException {
		ResultSet result=con.getData("SELECT uid FROM users WHERE email = '"+email+"'");
		if(result.next()) {
			uid=result.getInt("uid");	
			return true;
		}else {
			responce.setCode(0);
			responce.setMessage("Problem will posting.");
		}
		
		return false;
	}
	
	private int setdata() throws SQLException {
		StringBuilder qualification=new StringBuilder(),skills=new StringBuilder();
		
		for(String str:poRequest.getQualification()) {
			qualification.append(str+"^");
		}
		
		for(String str:poRequest.getSkills()) {
			skills.append(str+"^");
		}
		
		int result=con.insertData("insert into job_posts (`uid`,`title`,`company`,`location`,`qualification`,`experence`,`skills`,`description`,`responsibilities`,`contact`,`postedDate`) values("+uid+",\""+poRequest.getTitle()+"\",\""+poRequest.getCompanyName()+"\",\""+poRequest.getLocation()+"\",\""+qualification.toString()+"\",\""+poRequest.getExperence()+"\",\""+skills.toString()+"\",\""+poRequest.getDescription()+"\",\""+poRequest.getResponsibilities()+"\",\""+poRequest.getContact()+"\",now())");
		
		return result;
	}
	
	private void sendUpdatesToUser() throws SQLException, AddressException, MessagingException, IOException {
		ResultSet result=con.getData("select email,skills from users where getUpdadtes=true");
		List<String> emailsSendUpdate=new ArrayList<>();
		
		while(result.next()) {
			String skillUser=result.getString("skills");
			
			if(skillUser!=null) {
				
				boolean cou=true;
				skillUser=skillUser.toLowerCase();
				for(int i=0;i<poRequest.getSkills().size()&&cou;i++) {
					cou=(skillUser.contains(poRequest.getSkills().get(i).toLowerCase()));
				}
				
				if(cou) {
					emailsSendUpdate.add(result.getString("email").trim());
				}
			}
		}
		
		if(emailsSendUpdate.size()>=1) {
			SendMail send=new SendMail();
			send.send(poRequest.getTitle(), poRequest.getCompanyName(), emailsSendUpdate);
		}
	}

}
