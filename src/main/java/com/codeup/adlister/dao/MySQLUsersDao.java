package com.codeup.adlister.dao;

import com.codeup.adlister.Config;
import com.codeup.adlister.models.Response;
import com.codeup.adlister.models.ResponseError;
import com.codeup.adlister.models.User;
import com.codeup.adlister.models.Validation;
import com.codeup.adlister.util.Password;
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
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("SELECT * FROM users WHERE username = ? LIMIT 1");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                return extractUser(rs);
            }
        } catch (SQLException e) {
            return null;
        }

        return null;
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

    @Override
    public boolean update(User user) {
        String query = "UPDATE users SET username = ?, email= ?, password= ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setLong(4 , user.getId());

            return stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating user", e);
        }
    }

    @Override
    public User findById(Long id) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM users where id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                return extractUser(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User extractUser(ResultSet rs) throws SQLException {
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

    @Override
    public Validation editUser(Long id, String username, String email, String password, String passwordConfirmation) {

        Validation validate = new Validation();

        // Check original user data
        User user  = DaoFactory.getUsersDao().findById(id);

        validate.checkString("Username", username, false, 1, 100);
        validate.checkEmail("Email", email);

        // changing password
        if (!password.isEmpty()) {
            validate.checkAndComparePassword("Password", password, passwordConfirmation);
            password = Password.hash(password);
        } else {
            password = user.getPassword();
        }

        // change username
        if(!user.getUsername().equals(username)) {
            validate.checkValueExists("Username", username, false,
                    DaoFactory.getUsersDao().userExistsByUsername(username));
        }

        // If validation passes save user.
        if (validate.passed()) {
            user = new User(id, username, email, password);
            update(user);
        }

        return validate;

    }

}
