package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();

    // Display adds in specific category
    List<Ad> category(Long id);

    // insert a new ad and return the new ad's id
    Long insert(Ad ad);




    Ad findById(String id);
    List<Ad> findAllByUser(Long userId);
}
