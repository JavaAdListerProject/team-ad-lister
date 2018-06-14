package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.sql.SQLException;
import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();

    Object search(int catInt);

    // Display adds in specific category
    List<Ad> category(Long id);

    // insert a new ad and return the new ad's id
    Long insert(Ad ad);


    List<Ad> search(String search);
    void deleteQuery(String adId);


    Ad findById(String id);

    void updateAd(Ad ad);

    void create(Ad ad) throws SQLException;
    void update(Ad ad) throws SQLException;
    List<Ad> getAdsByTerm(String search);
    List<Ad> getAdsByCategory(String category);

    List<Ad> findAllByUser(Long userId);


}