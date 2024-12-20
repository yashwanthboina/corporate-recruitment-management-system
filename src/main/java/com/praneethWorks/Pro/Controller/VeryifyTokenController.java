package com.praneethWorks.Pro.Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.praneethWorks.Pro.JDBC_Connection.Connect;
import com.praneethWorks.Pro.utils.Hashing;
import com.praneethWorks.Pro.utils.StanderdResponce;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.*;

public class VeryifyTokenController extends HttpServlet{
	
	@Override
	public void service(HttpServletRequest req,HttpServletResponse res) {
		StanderdResponce responce=new StanderdResponce();
		try {
			if(verify(Hashing.doRevHashing(req.getParameter("token")))) {
				responce.setCode(1);
				responce.setMessage("Token Verified");
			}
			else {
				responce.setCode(0);
				responce.setMessage("Token not Verified");
			}
			
			res.getWriter().print(new Gson().toJson(responce));
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean verify(String data) throws ClassNotFoundException, SQLException {
		Connect con=new Connect();
		ResultSet result=con.getData("SELECT * FROM users WHERE email = '"+data+"'");
		return result.next();	
	}
}
