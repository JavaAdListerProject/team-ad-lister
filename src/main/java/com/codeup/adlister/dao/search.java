package com.codeup.adlister.dao;


import com.codeup.adlister.models.Ad;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet(name = "controllers.ViewProfileServlet", urlPatterns = "/search")

public class search extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ad ad = (Ad) request.getSession().getAttribute("ad");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        ad.setTitle(title);
        ad.setDescription(description);
        DaoFactory.getAdsDao().updateAd(ad);
        DaoFactory.getAdCategoryDao().linkCategories(ad.getId(), request.getParameterValues("category[]"));
        response.sendRedirect("/ads/search");
    }
}