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

    public List<Wish> getWishList() {
        return wishList;
    }

    public void setWishlistID(int wishlistID) {
        this.wishlistID = wishlistID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setWishlistName(String wishlistName) {
        this.wishlistName = wishlistName;
    }

    public void setWishList(List<Wish> wishList) {
        this.wishList = wishList;
    }
}
