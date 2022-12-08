package com.example.mobileprograming.model;

public class ContactItem {
    private int id;
    private String name;
    private String mobile;
    private boolean isFavorite;

    public ContactItem(int id, String name, String mobile, boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.isFavorite = isFavorite;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

}
