package com.userauth.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String pass = request.getParameter("password");
		PrintWriter out = response.getWriter();
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement("select*from users where username=? and password=?");
			pstmt.setString(1, username);
			pstmt.setString(2, pass);
			ResultSet res = pstmt.executeQuery();
			if (res.next()){
				out.print("Login Successfully");
			}
			else {
				out.print("something went wrong");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		
	}

}
