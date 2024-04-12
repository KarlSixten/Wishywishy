package org.example.wishywishy.repository;

import org.example.wishywishy.model.Wish;
import org.example.wishywishy.model.Wishlist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("dev")
class WishyRepositoryTest {

    @Autowired
    WishyRepository repository;

    @Test
    void createUser() {

    }

    @Test
    void updateWish() {
    }

    @Test
    void deleteWish() throws SQLException {
        repository.deleteWish(1);
        assertEquals(repository.findAllWishesInWishlist(1).size(), 1);
    }

    @Test
    void deleteWishList() {
        repository.deleteWishList(1);
        assertEquals(repository.getAllWishlistsFromUser("gustavSo").size(), 1);
    }

    @Test
    void addWishList() {
        Wishlist wishlist = new Wishlist("gustavSo", "liste 3");
        repository.addWishList(wishlist, "gustavSo");
        assertEquals(repository.getAllWishlistsFromUser("gustavSo").size(), 3);
    }

    @Test
    void addWish() {
        Wish wish = new Wish("Bowlingkugle", 1000, "bowlingkugle.dk", false);
        repository.addWish(wish, 1);
        assertEquals(repository.findAllWishesInWishlist(1).size(), 3);
    }

    @Test
    void findWishParfume() {
        Wish foundWish = repository.findWish(1);
        assertEquals("parfume", foundWish.getWishName());
    }

    @Test
    void findAllWishesInWishlist() {
    }

    @Test
    void getAllWishlistsFromUser() {
    }

    @Test
    void checkUniqueUsername() {
    }

    @Test
    void checkIfLoginValid() {
    }

    @Test
    void TESTprintAllUsers() {
    }

    @Test
    void toggleReserve() {
    }
}