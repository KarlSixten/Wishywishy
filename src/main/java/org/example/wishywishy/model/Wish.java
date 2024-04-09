package org.example.wishywishy.model;

import java.net.URL;

public class Wish {
    private String wishName;
    private String WishDescription;
    private double wishPrice;
    private URL url;

    public Wish(String wishName, String wishDescription, double wishPrice, URL url) {
        this.wishName = wishName;
        WishDescription = wishDescription;
        this.wishPrice = wishPrice;
        this.url = url;
    }
}
