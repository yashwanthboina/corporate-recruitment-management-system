package com.praneethWorks.Pro.JDBC_Connection;

import java.sql.*;

public class Connect {
	
	private Connection con;
	private Statement st;
	private ResultSet result;
	public  Connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://135.181.247.221:3306/tree", "praneeth", "Q0MJsX4yA@^H");
		st=con.createStatement();
		System.out.println("connected");
	}
	
	public ResultSet getData(String query) throws SQLException {
		return st.executeQuery(query);
	}
	
	public int insertData(String query) throws SQLException {
		return st.executeUpdate(query);
	}
	
	public void closeCon() throws SQLException {
		st.close();
		con.close();
	}
}
