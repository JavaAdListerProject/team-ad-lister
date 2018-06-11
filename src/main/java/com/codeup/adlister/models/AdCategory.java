package com.codeup.adlister.models;

public class AdCategory {
    private long id;
    private long mainId;
    private String title;


    public AdCategory(long id, long mainId, String title) {
        this.id = id;
        this.mainId = mainId;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public long getMainId() {
        return mainId;
    }

    public String getTitle() {
        return title;
    }




}


