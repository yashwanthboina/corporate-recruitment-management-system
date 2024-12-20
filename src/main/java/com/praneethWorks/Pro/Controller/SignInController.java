package com.praneethWorks.Pro.Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.praneethWorks.Pro.JDBC_Connection.Connect;
import com.praneethWorks.Pro.utils.Hashing;
import com.praneethWorks.Pro.utils.SignInResponce;
import com.praneethWorks.Pro.utils.SignIn_SignUp_Request;
import com.praneethWorks.Pro.utils.StanderdResponce;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignInController extends HttpServlet{
	
	Connect con=null;
	SignInResponce responce=null;
	
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
    		responce=new SignInResponce();
    		if(checkUser(obj.getEmail(),obj.getPassword())) {
    			responce.setCode(1);
				responce.setMessage("Account fetched successfully.");
				responce.setToken(Hashing.charHashing(obj.getEmail()));
    		}else {
				responce.setCode(0);
			}
    		
    		con.closeCon();
			res.getWriter().print(gson.toJson(responce));
			
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private boolean checkUser(String email,String password) throws ClassNotFoundException, SQLException {
		ResultSet result=con.getData("SELECT * FROM users WHERE email = '"+email+"'");
		
		if(result.next()) {
			if(result.getString("password").equals(password)) {
				return true;
			}else {
				responce.setMessage("Incorrect Password.");
			}
		}else {
			responce.setMessage("Account not found.");
		}
		return false;
	}

}
