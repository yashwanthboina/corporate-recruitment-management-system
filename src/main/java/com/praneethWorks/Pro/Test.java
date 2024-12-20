package com.praneethWorks.Pro;

import java.io.IOException;

import com.google.gson.Gson;
import com.praneethWorks.Pro.JavaMail.SendMail;

import java.util.*;

import javax.mail.MessagingException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Test extends HttpServlet{
	
	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		System.out.println("Coming");
		List<String> emails=new ArrayList<>();
		emails.add("praneethkulukuri@gmail.com");
		SendMail send=new SendMail();
		try {
			send.send("Hi", "hi", emails);
		} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = req.getReader().readLine()) != null) {
            requestBody.append(line);
        }
		Gson g=new Gson();
		Data d=g.fromJson(requestBody.toString(), Data.class);
		System.out.println(d.getEmail());
		res.getWriter().print(requestBody.toString());
	}

}
