package com.besant;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Result extends HttpServlet
{
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet res = null;
	String url = "jdbc:mysql://localhost:3306/varadhan";
	String un ="root";
	String ps = "V@95varadhan";
	String qry = "SELECT * FROM result WHERE rollnumber = ? AND name = ?";

	
	@Override
	public void init() throws ServletException {
	    try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Loaded Succesfully");
			con = DriverManager.getConnection(url, un, ps);
			System.out.println("Established Succesfully");
			 
			 
			
			
		} catch (ClassNotFoundException | SQLException e) {
		
		}
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		
		 PrintWriter out =   resp.getWriter();
		try {
			pstmt =   con.prepareStatement(qry);
			String num = req.getParameter("rn");
			int rp = Integer.parseInt(num);
			
			String name = req.getParameter("n");
			pstmt.setString(2, name);
		
			pstmt.setInt(1, rp);
			res = pstmt.executeQuery();
			
			if(res.next())
			{
				System.out.println(res.getString(1)+" "+res.getInt(2)+" "+res.getString(3)+" "+res.getInt(4) +" "+res.getInt(5) +" "+res.getInt(6));
				;
				 out.println("\r\n"
				 		+ "<html lang=\"en\">\r\n"
				 		+ "<head>\r\n"
				 		+ "    <meta charset=\"UTF-8\">\r\n"
				 		+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				 		+ "    <title>Document</title>\r\n"
				 		+ "</head>\r\n"
				 		+ "<body>\r\n"
				 		+ "\r\n"
				 		+ "    <table border=\"2\">\r\n"
				 		+ "     <tr>\r\n"
				 		+ "        <th>name</th>\r\n"
				 		+ "        <th>rollnumber</th>\r\n"
				 		+ "        <th>gender</th>\r\n"
				 		+ "        <th>dsp</th>\r\n"
				 		+ "        <th>m1</th>\r\n"
				 		+ "        <th>eia</th>\r\n"
				 		+ "        \r\n"
				 		+ "        \r\n"
				 		+ "\r\n"
				 		+ "     </tr>\r\n"
				 		+ "\r\n"
				 		+ "\r\n"
				 		+ "     <tr>\r\n"
				 		+ "        <td>"+ res.getString(1) +"</td>\r\n"
				 		+ "        <td>"+ res.getInt(2)  +"</td>\r\n"
				 		+ "        <td>"+ res.getString(3) +"</td>\r\n"
				 		+ "        <td>"+ res.getInt(4)+"</td>\r\n"
				 		+ "        <td>"+ res.getInt(5)+"</td>\r\n"
				 		+ "        <td>"+ res.getInt(6)+"</td>\r\n"
				 		+ "     </tr>\r\n"
				 		+ "\r\n"
				 		+ "\r\n"
				 		+ "    </table>\r\n"
				 		+ "    \r\n"
				 		+ "</body>\r\n"
				 		+ "</html>");
				 
			}
			else {
				
				out.print("\r\n"
						+ "<html lang=\"en\">\r\n"
						+ "<head>\r\n"
						+ "    <meta charset=\"UTF-8\">\r\n"
						+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
						+ "    <title>Document</title>\r\n"
						+ "</head>\r\n"
						+ "<body>\r\n"
						+ "\r\n"
						+ "   <h1>Invaild data</h1>\r\n"
						+ "</body>\r\n"
						+ "</html>");
			}
			
			
			
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
}