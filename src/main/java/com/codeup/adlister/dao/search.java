package com.codeup.adlister.dao;


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;
import java.util.*;


public class search extends HttpServlet{

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        System.out.println("MySQL Connect Example.");
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "userdetails";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "root";


        Statement st;
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName,userName,password);
            System.out.println("Connected to the database");


            ArrayList al=null;
            ArrayList emp_list =new ArrayList();
            String query =
                    "select * from Adlister_db ";

            System.out.println("query " + query);
            st = conn.createStatement();
            ResultSet  rs = st.executeQuery(query);


            while(rs.next()){
                al  = new ArrayList();

                al.add(rs.getString(1));
                al.add(rs.getString(2));
                al.add(rs.getString(3));
                al.add(rs.getString(4));
                al.add(rs.getString(5));
                al.add(rs.getString(6));
                al.add(rs.getString(7));
                al.add(rs.getString(8));
                al.add(rs.getString(10));
                System.out.println("al :: "+al);
                emp_list.add(al);
            }

            request.setAttribute("empList",emp_list);

            System.out.println("empList " + emp_list);

            // out.println("emp_list " + emp_list);

            String nextJSP = "/viewSearch.jsp";
            RequestDispatcher dispatcher =
                    getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request,response);
            conn.close();
            System.out.println("Disconnected from database");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
