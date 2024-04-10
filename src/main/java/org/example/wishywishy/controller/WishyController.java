package org.example.wishywishy.controller;

import org.example.wishywishy.model.User;
import org.example.wishywishy.model.Wish;
import org.example.wishywishy.model.Wishlist;
import org.example.wishywishy.service.WishyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("")
public class WishyController {
    private final WishyService wishyService;

    public WishyController(WishyService wishyService) {
        this.wishyService = wishyService;
    }


    @GetMapping("test")
    public String getTest() {
        return "test";
    }

    @GetMapping("login")
    public String getLogin(Model model, @RequestParam(name = "error", required = false) String error) {
        model.addAttribute("user", new User());
        if (error != null) {
            model.addAttribute("error");
        }
        return "login";
    }

    @PostMapping("login/submit")
    public String getSubmitLogin(@ModelAttribute User user) {
        if (wishyService.checkIfLoginValid(user) != null) {

            return "redirect:/user-front-page/" + user.getUsername();
        } else {
            return "redirect:/login?error";
        }
    }

    @GetMapping("login/createuser")
    public String getCreateUser(Model model, @RequestParam(name = "error", required = false) String error) {
        model.addAttribute("user", new User());
        if (error != null) {
            model.addAttribute("error");
        }
        return "createuser";
    }

    @PostMapping("login/createuser/save")
    public String getSaveNewUser(@ModelAttribute User newUser) {
        if (wishyService.createUser(newUser) != null) {
            return "redirect:/login";
        } else {
            return "redirect:/login/createuser?error";
        }
    }
    @GetMapping("addWishList")
    public String addWishList(Model model){
        model.addAttribute("wishList", new Wishlist());
        return "add-wishlist";
    }
    @GetMapping("addWish")
    public String addWish(Model model){
        model.addAttribute("wish", new Wish());
        return "test";
    }
    @PostMapping("addWishList")
    public String addWishList(@ModelAttribute Wishlist wishlist, String username){
        wishyService.addWishList(wishlist,username);
        return "redirect:/user-front-page/" + username;
    }
    @PostMapping("addWish")
    public String addWish(@ModelAttribute Wish wish, int wishListID){
        wishyService.addWish(wish,wishListID);
        return "test";
    }

   @GetMapping("{wishlistID}/delete")
    public String deleteWish(@PathVariable("name") int wishId) throws SQLException {
        Wish wishToDelete = wishyService.findWish(wishId);
        int wishID = wishToDelete.getWishID();
        wishyService.deleteWish(wishID);
        return "redirect:/test";
    }
    @GetMapping("user-front-page/{username}")
    public String getWishlistsFromUser(@PathVariable("username") String username, Model model){
        model.addAttribute("wishlists", wishyService.getAllWishlistsFromUser(username));
        return "user-front-page";
    }

    @GetMapping("see-wishlist/{username}/{wishlist}")
    public String seeWishlist(@PathVariable("username") String username, @PathVariable("wishlist") Wishlist wishlist, Model model){
        model.addAttribute("wishlist", wishyService.findAllWishesInWishlist(wishlist));
        return "see-wishlist";
    }
}
