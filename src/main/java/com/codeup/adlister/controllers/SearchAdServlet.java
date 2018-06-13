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


        // Render Search Page with Search function on it

        // if param search ! =null then show results of search

                // Ok not null, lets do the search
                // Search reached out to DAOFactory, runs a search function against that value provided by search
            // returns value and appends to page

        // Search is displayed on page


            String input = request.getParameter("term");
        System.out.println(input);

            if (input != null) {

                request.setAttribute("term", input);
                request.setAttribute("ads", DaoFactory.getAdsDao().search(input));
            }
                request.getRequestDispatcher("/WEB-INF/ads/search.jsp").forward(request, response);

        }
        // what happend with the submit and what going on with the search


    }