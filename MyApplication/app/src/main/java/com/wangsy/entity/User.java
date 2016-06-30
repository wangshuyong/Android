package com.wangsy.entity;

/**
 * Created by yangsy on 2016/6/29.
 */
public class User {
    private String image;
    private String info;
    private String titile;
    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
