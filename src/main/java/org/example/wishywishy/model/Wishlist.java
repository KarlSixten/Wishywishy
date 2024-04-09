package org.example.wishywishy.model;

import java.util.ArrayList;
import java.util.List;

public class Wishlist {
    private int wishlistID;
    private String username;
    private String wishlistName;
    private List<Wish> wishList = new ArrayList<>();

    public Wishlist(int wishlistID, String username, String wishlistName) {
        this.wishlistID = wishlistID;
        this.username = username;
        this.wishlistName = wishlistName;
    }

    public int getWishlistID() {
        return wishlistID;
    }

    public String getUsername() {
        return username;
    }

    public String getWishlistName() {
        return wishlistName;
    }
}
