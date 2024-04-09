package org.example.wishywishy.controller;

import org.example.wishywishy.model.Wish;
import org.example.wishywishy.model.Wishlist;
import org.example.wishywishy.service.WishyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping("")
public class WishyController {
    private final WishyService wishyService;

    public WishyController(WishyService wishyService) {
        this.wishyService = wishyService;
    }

    @GetMapping("test")
    public String getTest() throws IOException {
        return "test";
    }
    @GetMapping("addWishList")
    public String addWishList(Model model){
        model.addAttribute("wishList", new Wishlist());
        return "test";
    }
    @GetMapping("addWish")
    public String addWish(Model model){
        model.addAttribute("wish", new Wish());
        return "test";
    }
    @PostMapping("addWishList")
    public String addWishList(@ModelAttribute Wishlist wishlist, String username){
        wishyService.addWishList(wishlist,username);
        return "test";
    }
    @PostMapping("addWish")
    public String addWish(@ModelAttribute Wish wish, int wishListID){
        wishyService.addWish(wish,wishListID);
        return "test";
    }

   @GetMapping("{name}/delete")
    public String deleteWish(@PathVariable("name") int wishId) throws SQLException {
        Wish wishToDelete = wishyService.findWish(wishId);
        int wishID = wishToDelete.getWishID();
        wishyService.deleteWish(wishID);
        return "redirect:/test";
    }
}
