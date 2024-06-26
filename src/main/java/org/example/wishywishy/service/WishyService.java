package org.example.wishywishy.service;

import org.example.wishywishy.model.User;
import org.example.wishywishy.model.Wish;

import org.example.wishywishy.model.Wishlist;


import org.example.wishywishy.repository.WishyRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class WishyService {
    private final WishyRepository wishyRepository;

    public WishyService(WishyRepository wishyRepository) {
        this.wishyRepository = wishyRepository;
    }

    public User createUser(User newUser) {
        return wishyRepository.createUser(newUser);
    }

    public User checkIfLoginValid(User userToCheck) {
        return wishyRepository.checkIfLoginValid(userToCheck);
    }

    public void addWishList(Wishlist wishlist, String username) {
        wishyRepository.addWishList(wishlist, username);
    }

    public void addWish(Wish wish, int wishListID) {
        wishyRepository.addWish(wish, wishListID);
    }

    public void deleteWish(int wishId) throws SQLException {
        wishyRepository.deleteWish(wishId);
    }

    public void deleteWishlist(int wishlistID) {
        wishyRepository.deleteWishList(wishlistID);
    }

    public Wish findWish(int wishID) {
        return wishyRepository.findWish(wishID);
    }

    public Wish updateWish(Wish updatedWish) {
        return wishyRepository.updateWish(updatedWish);
    }

    public List<Wish> findAllWishesInWishlist(int wishlistID){
        return wishyRepository.findAllWishesInWishlist(wishlistID);
    }
    public List <Wishlist> getAllWishlistsFromUser(String username){
        return wishyRepository.getAllWishlistsFromUser(username);
    }

    public void toggleReserve(boolean isReserved, int wishId) {
        wishyRepository.toggleReserve(isReserved, wishId);
    }

    public List<User> getAllUsers() {
        return wishyRepository.getAllUsers();
    }

    public String getWishlistName(int wishlistid) {
        return wishyRepository.getWishlistName(wishlistid);
    }
}
