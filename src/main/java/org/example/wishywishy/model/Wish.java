package org.example.wishywishy.model;

import java.net.URL;

public class Wish {
    private String wishName;
    private double wishPrice;
    private String url;
    private int wishID;
    private boolean isReserved;

    public Wish(String wishName, double wishPrice, String url, int wishID, boolean isReserved) {

        this.wishName = wishName;
        this.wishPrice = wishPrice;
        this.url = url;
        this.wishID = wishID;
        this.isReserved = isReserved;
    }

    public Wish(String wishName, double wishPrice, String url, boolean isReserved) {

        this.wishName = wishName;
        this.wishPrice = wishPrice;
        this.url = url;
        this.isReserved = isReserved;
    }
    public Wish(){

    }

    public String getWishName() {
        return wishName;
    }

    public void setWishName(String wishName) {
        this.wishName = wishName;
    }


    public double getWishPrice() {
        return wishPrice;
    }

    public void setWishPrice(double wishPrice) {
        this.wishPrice = wishPrice;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWishID() {
        return wishID;
    }

    public void setWishID(int wishID) {
        this.wishID = wishID;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }
}
