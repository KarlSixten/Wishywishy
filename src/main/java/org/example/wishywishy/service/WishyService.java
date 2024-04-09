package org.example.wishywishy.service;

import org.example.wishywishy.model.Wish;
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

    public Wish findWish(int wishID) {
        return wishyRepository.findWish(wishID);
    }

    public Wish updateWish(Wish updatedWish) {
        return wishyRepository.updateWish(updatedWish);
    }
}
