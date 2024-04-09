package org.example.wishywishy.model;

import java.net.URL;

public class Wish {
    private String wishName;
    private String WishDescription;
    private double wishPrice;
    private URL url;
    private int wishID;

    public Wish(String wishName, String wishDescription, double wishPrice, URL url) {
        this.wishName = wishName;
        WishDescription = wishDescription;
        this.wishPrice = wishPrice;
        this.url = url;
    }

    public String getWishName() {
        return wishName;
    }

    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

    public String getWishDescription() {
        return WishDescription;
    }

    public void setWishDescription(String wishDescription) {
        WishDescription = wishDescription;
    }

    public double getWishPrice() {
        return wishPrice;
    }

    public void setWishPrice(double wishPrice) {
        this.wishPrice = wishPrice;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public int getWishID() {
        return wishID;
    }

    public void setWishID(int wishID) {
        this.wishID = wishID;
    }
}
