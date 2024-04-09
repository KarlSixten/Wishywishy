package org.example.wishywishy.model;

import java.net.URL;

public class Wish {
    private int wishID;
    private String wishName;
    private String WishDescription;
    private double wishPrice;
    private URL url;

    public Wish(int wishID, String wishName, String wishDescription, double wishPrice, URL url) {
        this.wishID = wishID;
        this.wishName = wishName;
        WishDescription = wishDescription;
        this.wishPrice = wishPrice;
        this.url = url;
    }

    public int getWishID() {
        return wishID;
    }

    public String getWishName() {
        return wishName;
    }

    public String getWishDescription() {
        return WishDescription;
    }

    public double getWishPrice() {
        return wishPrice;
    }

    public URL getUrl() {
        return url;
    }
}
