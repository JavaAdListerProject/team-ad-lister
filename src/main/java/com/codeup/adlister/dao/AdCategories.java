package com.codeup.adlister.dao;

import com.codeup.adlister.models.AdCategory;

import java.util.List;

public interface AdCategories {

    // Returns all ad categories
    List<AdCategory> all();

    // Returns only main ad categories
    List<AdCategory> main();

    // Returns sub category of main category
    List<AdCategory> sub(long id);

    // Returns main, then sub in list order for printing out.
    List<AdCategory> inListOrder();
}
