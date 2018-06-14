package com.codeup.adlister.dao;

import com.codeup.adlister.Config;
import com.codeup.adlister.models.Ad;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MySQLAdsDao implements Ads {
    private static Connection connection = null;
   public static String upDate = null;


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
    public List<Ad> search(String search) {
        PreparedStatement stmt = null;
        try {
            System.out.println(search);
            stmt = connection.prepareStatement("SELECT * FROM ads where title LIKE ? ");
            stmt.setString(1, "%" + search + "%");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public void deleteQuery(String adId) {

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

    @Override
    public Ad findById(String adId) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads where id = ?");
            stmt.setLong(1, Long.parseLong(adId));
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                return extractAd(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void updateAd(Ad ad) {

    }

    @Override
    public void create(Ad ad) throws SQLException {

    }

    @Override
    public void update(Ad ad) throws SQLException {

    }

    @Override
    public List<Ad> getAdsByTerm(String search) {
        return null;
    }

    @Override
    public List<Ad> getAdsByCategory(String category) {
        return null;
    }

    @Override
    public Object search(int catInt) {
        return null;
    }

    private static Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getString("title"),
                rs.getString("description")
        );
    }

    @Override
    public List<Ad> findAllByUser(Long userId) {

        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads WHERE user_id = ?");
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }


    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }

    @Override
    public List<Ad> category(Long Id) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads WHERE category_id = ?");
            stmt.setLong(1, Id);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving ads in category.", e);
        }
    }


    public void deleteAd(Ad ad) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM ads_categories WHERE ad_id = " + ad.getId());
            statement.executeUpdate("DELETE FROM ads WHERE ID = " + ad.getId());
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting this ad.", e);
        }
    }



}
