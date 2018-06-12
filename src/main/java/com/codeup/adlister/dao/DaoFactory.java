package com.codeup.adlister.dao;

import com.codeup.adlister.Config;

public class DaoFactory {
    private static Ads adsDao;
    private static Users usersDao;
    private static AdCategories adsCategoryDao;

    private static Config config = new Config();

    public static Ads getAdsDao() {
        if (adsDao == null) {
            adsDao = new MySQLAdsDao(config);
        }
        return adsDao;
    }

    public static AdCategories getAdCategoryDao() {
        if (adsCategoryDao == null) {
            adsCategoryDao = new MySQLAdCategoriesDao(config);
        }
        return adsCategoryDao;
    }

    public static Users getUsersDao() {
        if (usersDao == null) {
            usersDao = new MySQLUsersDao(config);
        }
        return usersDao;
    }


}
