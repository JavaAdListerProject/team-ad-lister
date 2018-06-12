package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.AdsListDisplayServlet", urlPatterns = "/ads/list")
public class AdsCategoryDisplayServlet extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            if(request.getParameter("cat") != null) {
                Long id = Long.parseLong(request.getParameter("cat"));
                request.setAttribute("ads", DaoFactory.getAdsDao().category(id));
                request.getRequestDispatcher("/WEB-INF/ads/list.jsp").forward(request, response);
            } else {
                response.sendRedirect("/main");
                return;
            }

        }
}


