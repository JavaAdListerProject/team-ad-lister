package com.codeup.adlister.dao;

import com.codeup.adlister.Config;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import java.sql.PreparedStatement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    public MySQLAdsDao(Config config) {
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
    public List<Ad> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public Long insert(Ad ad) {
        try {
            String insertQuery = "INSERT INTO ads(user_id, title, description) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.getUserId());
            stmt.setString(2, ad.getTitle());
            stmt.setString(3, ad.getDescription());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

    // SEARCH FUNCTON

    @Override
    public List<Ad> getAds(String search) {
        try {
            String searchQuery = "SELECT * FROM ads WHERE title LIKE ? OR description LIKE ?";
            PreparedStatement stmt = connection.prepareStatement(searchQuery);
            stmt.setString(1, "%" + search + "%");
            stmt.setString(2, "%" + search + "%");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving ads.", e);
        }
    }

    @Override
    public List<Ad> byUsername(BIConversion.User user) {
        return null;
    }

    @Override
    public List<Ad> byUsername(User user) {
        return null;
    }


    public Ad getAdById(int idToFind) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM ads WHERE ID = " + idToFind);
            rs.next();
            return extractAd(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving details of ad.", e);
        }
    }


        @Override
        public void deleteAd(Ad ad) {
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate("DELETE FROM ads WHERE ID = " + ad.getId());
            } catch (SQLException e) {
                throw new RuntimeException("Error deleting this ad.", e);
            }
        }



    @Override
    public void updateAd(Ad ad) {

    }

    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getString("title"),
            rs.getString("description")
        );
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }
}
