package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Adspageservlet", urlPatterns = "/adpage")
public class AdsPageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Ad ad = DaoFactory.getAdsDao().findById(request.getParameter("adid"));
        User user =  DaoFactory.getUsersDao().findById(ad.getUserId());


        request.setAttribute("adid", request.getParameter("adid"));
        request.setAttribute("selectad", ad);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/ads/adpage.jsp").forward(request, response);
    }
}
