package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();
    // insert a new ad and return the new ad's id
    Long insert(Ad ad);

    // for searching Ads =>

    List<Ad> getAds(String search);

    List<Ad> byUsername(BIConversion.User user);
    Ad getAdById(int findId);  // idToFind

    void deleteAd(Ad ad);
    void updateAd(Ad ad);

    // end of add search
}
