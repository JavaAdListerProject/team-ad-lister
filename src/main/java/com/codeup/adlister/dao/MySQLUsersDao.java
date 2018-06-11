package com.codeup.adlister.dao;

import com.codeup.adlister.Config;
import com.codeup.adlister.models.Response;
import com.codeup.adlister.models.ResponseError;
import com.codeup.adlister.models.User;
import com.codeup.adlister.models.Validation;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class MySQLUsersDao implements Users {
    private Connection connection;

    public MySQLUsersDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                config.getUrl(),
                config.getUser(),
                config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }



    @Override
    public User findByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            return extractUser(stmt.executeQuery());
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Long insert(User user) {
        String query = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating new user", e);
        }
    }

    private User extractUser(ResultSet rs) throws SQLException {
        if (! rs.next()) {
            return null;
        }
        return new User(
            rs.getLong("id"),
            rs.getString("username"),
            rs.getString("email"),
            rs.getString("password")
        );
    }


    /* Checks if user exist by username. */
    public boolean userExistsByUsername(String username ) {
        return (findByUsername(username) != null);

    }


    public Validation addNewUser(String username, String email, String password, String passwordConfirmation) {

        Validation validate = new Validation();

        validate.checkString("Username", username, false, 1, 100);
        validate.checkEmail("Email", email);
        validate.checkAndComparePassword("Password", password, passwordConfirmation);
        validate.checkValueExists("Username", username, false,
                DaoFactory.getUsersDao().userExistsByUsername(username));

        // If validation passes save user.
        if (validate.passed()) {
            User user = new User(username, email, password);
            insert(user);
        }

        return validate;

    }

}
