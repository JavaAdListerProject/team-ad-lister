package com.codeup.adlister;


/* Change SampleConfig class to CONFIG class*/
public class SampleConfig {
    private String url;
    private String user;
    private String password;


    public SampleConfig() {
        this.url = "jdbc:mysql://localhost:3306/XXXXX?serverTimezone=UTC&useSSL=false";
        this.user = "XXXXXXXXX";
        this.password = "XXXXXXXXXX";
    }


    public SampleConfig(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }







}
