package com.praneethWorks.Pro.Controller;

import java.sql.SQLException;
import java.io.IOException;
import java.sql.ResultSet;
import com.praneethWorks.Pro.JDBC_Connection.Connect;
import com.praneethWorks.Pro.utils.SignIn_SignUp_Request;
import com.praneethWorks.Pro.utils.StanderdResponce;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.*;

public class SignUpController extends HttpServlet{
	
	Connect con=null;
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = req.getReader().readLine()) != null) {
            requestBody.append(line);
        }
        
        Gson gson=new Gson();
        SignIn_SignUp_Request obj=new SignIn_SignUp_Request();
        obj=gson.fromJson(requestBody.toString(), SignIn_SignUp_Request.class);
        
        try {
    		con=new Connect();
    		StanderdResponce responce=new StanderdResponce();
			if(!checkUser(obj.getEmail())) {
				int x=createAccount(obj.getName(), obj.getEmail(), obj.getPassword());
				
				if(x>=1) {
					responce.setCode(1);
					responce.setMessage("Account Created.");
				}
				
			}else {
				responce.setCode(0);
				responce.setMessage("Account Already Exists.");
			}
			
			con.closeCon();
			res.getWriter().print(gson.toJson(responce));
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean checkUser(String email) throws ClassNotFoundException, SQLException {
		ResultSet result=con.getData("SELECT * FROM users WHERE email = '"+email+"'");
		return result.next();
	}
	
	private int createAccount(String name,String email,String password) throws SQLException {
		int x=con.insertData("insert into users (`name`,`email`,`password`) values('"+name+"','"+email+"','"+password+"')");
		return x;
	}
}
