package com.example.fragments;

import java.io.Serializable;

public class Songs implements Serializable {
    private String name = "";
    private String description = "";
    private boolean favorite;

    public Songs(String name, String description) {
        this.name = name;
        this.description = description;
        this.favorite = true;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }
    public boolean getFavorite() {
        return this.favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
