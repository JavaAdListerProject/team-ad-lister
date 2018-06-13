package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.SearchAdServlet", urlPatterns = "/ads/search")
public class SearchAdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String input = request.getParameter("term");
        System.out.println(input);

        if (input != null) {

            request.setAttribute("term", input);
            request.setAttribute("ads", DaoFactory.getAdsDao().search(input));
            request.setAttribute("sticky", input);
        }
        request.getRequestDispatcher("/WEB-INF/ads/search.jsp").forward(request, response);

    }
}