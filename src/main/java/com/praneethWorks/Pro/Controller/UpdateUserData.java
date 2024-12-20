package com.praneethWorks.Pro.Controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.*;
import com.praneethWorks.Pro.JDBC_Connection.Connect;
import com.praneethWorks.Pro.utils.StanderdResponce;
import com.praneethWorks.Pro.utils.UpdateUserDetailsRequest;

public class UpdateUserData extends HttpServlet{
	
	private UpdateUserDetailsRequest upData;
	private StanderdResponce responce;
	private Connect con;
	
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = req.getReader().readLine()) != null) {
            requestBody.append(line);
        }
        
        upData=new Gson().fromJson(requestBody.toString(), UpdateUserDetailsRequest.class);
        responce=new StanderdResponce();
        
        try {
			con=new Connect();
			updateData();
			con.closeCon();
			res.getWriter().print(new Gson().toJson(responce));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void updateData() throws SQLException {
		
		int result=0;
		if(upData.getSkills().size()>=1)
		{
			StringBuilder skills = new StringBuilder();
			for(String str:upData.getSkills()) {
				skills.append(str+"^");
			}
			
			result=con.insertData("update users set skills='"+skills+"',getUpdadtes="+upData.isGetUpates()+" where uid="+upData.getUid());
		}else {
			result=con.insertData("update users set skills=null,getUpdadtes="+upData.isGetUpates()+" where uid="+upData.getUid());
		}
		if(result>=1) {
			responce.setCode(1);
			responce.setMessage("Account Updated.");
		}else {
			responce.setCode(0);
			responce.setMessage("Account not Updated.");
		}
	}

}
