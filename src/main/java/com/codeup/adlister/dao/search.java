//package com.codeup.adlister.dao;
//
//
//import com.codeup.adlister.models.Ad;
//import com.codeup.adlister.models.User;
//
//import java.io.*;
//import javax.servlet.*;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//import javax.sql.*;
//import java.sql.*;
//import java.util.*;
//
//@WebServlet(name = "controllers.ViewProfileServlet", urlPatterns = "/search")
//
//public class search extends HttpServlet {
//     protected void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//         String deleteAD = request.getSession().getAttribute("user");
//            Ad ad = new Ad(
//                    user.getId(),
//                    request.getParameter("title"),
//                    request.getParameter("description")
//            );
//            DaoFactory.getAdsDao().insert(ad);
//            response.sendRedirect("/ads");
//        }
//    }
//
//
