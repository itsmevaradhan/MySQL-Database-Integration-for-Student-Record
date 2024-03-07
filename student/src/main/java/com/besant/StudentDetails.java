package com.besant;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentDetails extends HttpServlet
{
	Connection con = null;
	PreparedStatement pstmt = null;
	String url = "jdbc:mysql://localhost:3306/varadhan";
	String un ="root";
	String ps = "V@95varadhan";
	String qry = "insert into result(name,rollnumber,gender,dsp,m1,eia) values (?,?,?,?,?,?)";

	
	
	
	
	@Override
	public void init() throws ServletException {
	    try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Loaded Succesfully");
			
			con = DriverManager.getConnection(url, un, ps);
			System.out.println("Established Succesfully");
			 
			 
			
			
		} catch (ClassNotFoundException | SQLException e) {
			 e.printStackTrace();
		}
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		
		try {
			pstmt =   con.prepareStatement(qry);
			String name = req.getParameter("n");
			
			String pr = req.getParameter("rn");
			int rollnumber  = Integer.parseInt(pr);
			
			String gender = req.getParameter("g");
			
			
			String dr = req.getParameter("d");
			int dsp  = Integer.parseInt(dr);
			
			
			String mr = req.getParameter("m");
			int m1  = Integer.parseInt(mr);
			
			String er = req.getParameter("e");
			int eia  = Integer.parseInt(er);
			
			pstmt.setString(1,name);
			pstmt.setInt(2, rollnumber);
			pstmt.setString(3, gender);
			pstmt.setInt(4, dsp);
			pstmt.setInt(5, m1);
			pstmt.setInt(6, eia);
			
			pstmt.executeUpdate();
			
			System.out.println(name +" "+rollnumber+" "+gender+" "+dsp+" "+m1+" "+eia);
			
		
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
}