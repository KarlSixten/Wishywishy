package org.example.wishywishy.service;

import org.example.wishywishy.model.Wish;
import org.example.wishywishy.model.Wishlist;
import org.example.wishywishy.repository.WishyRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class WishyService {
    private final WishyRepository wishyRepository;

    public WishyService(WishyRepository wishyRepository) {
        this.wishyRepository = wishyRepository;
    }

public void deleteWish(int wishId) throws SQLException {
    wishyRepository.deleteWish(wishId);
}
public void addWishList(Wishlist wishlist, String username){
    wishyRepository.addWishList(wishlist, username);
}
public void addWish(Wish wish, int wishListID){
        wishyRepository.addWish(wish,wishListID);
}

}
