package org.example.wishywishy.model;

public class Wishlist {
    private String username;
    private String wishlistName;

    private int wishListID;

    public Wishlist(String username, String wishlistName){
        this.username = username;
        this.wishlistName = wishlistName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWishlistName() {
        return wishlistName;
    }

    public void setWishlistName(String wishlistName) {
        this.wishlistName = wishlistName;
    }

    public int getWishListID() {
        return wishListID;
    }

    public void setWishListID(int wishListID) {
        this.wishListID = wishListID;
    }
}
