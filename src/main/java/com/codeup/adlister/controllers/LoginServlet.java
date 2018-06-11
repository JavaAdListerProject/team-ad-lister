package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.models.Validation;
import com.codeup.adlister.util.Password;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect("/profile");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Validation validate = new Validation();

        User user = DaoFactory.getUsersDao().findByUsername(username);

        validate.checkString("Username", username,false);
        validate.checkExists("Username", true, DaoFactory.getUsersDao().userExistsByUsername(username));
        validate.checkString("Password", password, false);


        if(user != null) {
            validate.checkEncryptedPassword(password, user.getPassword());
        }

        if (!validate.passed()) {
            request.setAttribute("validate", validate);
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }


            request.getSession().setAttribute("user", user);
            response.sendRedirect("/profile");



    }
}
