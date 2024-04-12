package org.example.wishywishy.repository;

import org.example.wishywishy.model.User;
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
        repository.createUser(new User("test", "test"));
        assertEquals(repository.getAllUsers().size(), 5);
    }

    @Test
    void updateWish() {
        Wish wishupdated = new Wish("Bowlingkugle", 1000, "bowlingkugle.dk",1, false);
        repository.updateWish(wishupdated);
        assertEquals("Bowlingkugle", repository.findWish(1).getWishName());
    }

    @Test
    void deleteWish() throws SQLException {
        repository.deleteWish(1);
        assertEquals(1, repository.findAllWishesInWishlist(1).size());
    }

    @Test
    void deleteWishList() {
        repository.deleteWishList(1);
        assertEquals(2,repository.getAllWishlistsFromUser("gustavSo").size());
    }

    @Test
    void addWishList() {
        Wishlist wishlist = new Wishlist("gustavSo", "liste 3");
        repository.addWishList(wishlist, "gustavSo");
        assertEquals(4,repository.getAllWishlistsFromUser("gustavSo").size());
    }

    @Test
    void addWish() {
        Wish wish = new Wish("Bowlingkugle", 1000, "bowlingkugle.dk", false);
        repository.addWish(wish, 1);
        assertEquals(3, repository.findAllWishesInWishlist(1).size());
    }

    @Test
    void findWishWatch() {
        Wish foundWish = repository.findWish(1);
        assertEquals("Watch", foundWish.getWishName());
    }

    @Test
    void findAllWishesInWishlist() {
        assertEquals(2, repository.findAllWishesInWishlist(1).size());

    }

    @Test
    void getAllWishlistsFromUser() {
        int expected = repository.getAllWishlistsFromUser("gustavSo").size();
        assertEquals(3, expected);
    }

    @Test
    void checkUniqueUsername() {
       User test = new User("testUser", "Test123");
       boolean expected = repository.checkUniqueUsername(test);
       assertEquals(false, expected);
    }

    @Test
    void checkIfLoginValidFail() {
        User test = new User("testUser", "Invalid password");
        User expected = repository.checkIfLoginValid(test);
        assertEquals(null, expected);
    }

    @Test
    void checkIfLoginValidSuccess() {
        User test = new User("testUser", "Test123");
        User actual = repository.checkIfLoginValid(test);
        assertEquals("testUser", actual.getUsername());
    }

    @Test
    void getAllUsers() {
        int actual = repository.getAllUsers().size();
        assertEquals( 4,actual);
    }

    @Test
    void toggleReserve() {
        repository.toggleReserve(true, 1);
        boolean actual = repository.findWish(1).isReserved();
        assertEquals(true, actual);
    }
}