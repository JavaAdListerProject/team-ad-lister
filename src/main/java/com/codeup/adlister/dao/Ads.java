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


    List<Ad> search(String search);
    List<Ad> searhbyCat(int catId);
    Ad searchbyAdId(int id);
    List<Ad> searchByUserId(long userId);


    Ad findById(String id);

    Object search(int catInt);
}
