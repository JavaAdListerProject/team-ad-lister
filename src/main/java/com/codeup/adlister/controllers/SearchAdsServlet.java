package com.codeup.adlister.controllers;


import com.codeup.adlister.models.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SearchAdsServlet", urlPatterns = "/search")
public class SearchAdsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ad search = (Ad) request.getSession().getAttribute("search");

        if(search != null) {
            response.sendRedirect("/ads/search");
    }
        request.getRequestDispatcher("/WEB-INF/ads/.jsp").forward(request, response);
    }
}
