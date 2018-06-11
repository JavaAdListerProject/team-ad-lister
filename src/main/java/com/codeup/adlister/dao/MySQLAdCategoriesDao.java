package com.codeup.adlister.dao;

import com.codeup.adlister.Config;
import com.codeup.adlister.models.AdCategory;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdCategoriesDao implements AdCategories {
    private Connection connection = null;

    public MySQLAdCategoriesDao(Config config) {
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
    public List<AdCategory> all() {
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement("SELECT * FROM categories");
            ResultSet rs = stmt.executeQuery();
            return createAdCategoriesFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all categories.", e);
        }

    }

    @Override
    public List<AdCategory> main() {
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement("SELECT * FROM categories WHERE main_id = 0");
            ResultSet rs = stmt.executeQuery();
            return createAdCategoriesFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving main categories.", e);
        }
    }

    @Override
    public List<AdCategory> sub(long id) {
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement("SELECT * FROM categories WHERE main_id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            return createAdCategoriesFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving sub categories.", e);
        }
    }

    @Override
    public List<AdCategory> inListOrder() {
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(
                    "SELECT  c.id,\n" +
                    "        coalesce(c.main_id, c1.main_id) AS main_id,\n" +
                    "        c.title AS title\n" +
                    "\n" +
                    "FROM categories c\n" +
                    " LEFT OUTER JOIN categories c1 ON (c1.id = c.main_id)\n" +
                    "ORDER BY coalesce(c1.title, c.title), c1.main_id\n");

            ResultSet rs = stmt.executeQuery();
            return createAdCategoriesFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all categories.", e);
        }

    }

    private AdCategory extractAdCategory(ResultSet rs) throws SQLException {
        return new AdCategory (
                rs.getLong("id"),
                rs.getLong("main_id"),
                rs.getString("title")
        );
    }

    private List<AdCategory> createAdCategoriesFromResults(ResultSet rs) throws SQLException {
        List<AdCategory> adCategories = new ArrayList<>();
        while (rs.next()) {
            adCategories.add(extractAdCategory(rs));
        }
        return adCategories;


    }
}
