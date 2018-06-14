package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.models.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.ViewProfileServlet", urlPatterns = "/profile")
public class ViewProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }

        User user = (User) request.getSession().getAttribute("user");
        List ads = DaoFactory.getAdsDao().findAllByUser(user.getId());


        request.setAttribute("ads", ads);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }

        // Logged in user to object
        User user = (User) request.getSession().getAttribute("user");
        List ads = DaoFactory.getAdsDao().findAllByUser(user.getId());
        request.setAttribute("ads", ads);
        request.setAttribute("user", user);

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("confirm_password");

        Validation validate = DaoFactory.getUsersDao().editUser(user.getId() ,username, email, password, passwordConfirmation);

        if (!validate.passed()) {
            request.setAttribute("validate", validate);
        } else {
            request.setAttribute("updated", true);
        }


        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
    }


}