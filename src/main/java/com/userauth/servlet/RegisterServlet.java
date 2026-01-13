package com.userauth.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Read form data
        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        String email = request.getParameter("email");

        // 2. Get writer to send response
        PrintWriter out = response.getWriter();

        // 3. Get database connection
        Connection con = DBConnection.getConnection();

        try {
            // 4. SQL query
            PreparedStatement pstmt = con.prepareStatement(
                "INSERT INTO users(username, password, email) VALUES (?, ?, ?)"
            );

            pstmt.setString(1, name);
            pstmt.setString(2, pass);
            pstmt.setString(3, email);

            // 5. Execute query
            int i = pstmt.executeUpdate();

            if (i > 0) {
                out.print("Registered Successfully");
            } else {
                out.print("Something went wrong");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            out.print("Database error");
        }
    }
}
