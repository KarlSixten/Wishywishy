package org.example.wishywishy.controller;

import jakarta.servlet.http.HttpSession;
import org.example.wishywishy.model.User;
import org.example.wishywishy.model.Wish;
import org.example.wishywishy.model.Wishlist;
import org.example.wishywishy.service.WishyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;

@Controller
@RequestMapping("")
public class WishyController {
    private final WishyService wishyService;
    private final HttpSession httpSession;

    public WishyController(WishyService wishyService, HttpSession httpSession) {
        this.wishyService = wishyService;
        this.httpSession = httpSession;
    }

    @GetMapping("")
    public String getLogin(Model model, @RequestParam(name = "error", required = false) String error) {
        httpSession.invalidate();
        model.addAttribute("user", new User());
        if (error != null) {
            model.addAttribute("error");
        }
        return "login";
    }

    @PostMapping("login/submit")
    public String getSubmitLogin(@ModelAttribute User user, HttpSession httpSession) {
        if (wishyService.checkIfLoginValid(user) != null) {
            httpSession.setAttribute("user", user);
            return "redirect:/" + user.getUsername();
        } else {
            return "redirect:/?error";
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
            return "redirect:/";
        } else {
            return "redirect:/login/createuser?error";
        }
    }

    @GetMapping("{username}")
    public String getWishlistsFromUser(@PathVariable("username") String username, Model model){
        model.addAttribute("wishlists", wishyService.getAllWishlistsFromUser(username));
        return "user-front-page";
    }

    @GetMapping("{username}/addWishList/")
    public String addWishList(Model model, @PathVariable("username") String username){
        model.addAttribute("wishList", new Wishlist());
        return "add-wishlist";
    }

    @PostMapping("addWishList")
    public String addWishList(@ModelAttribute Wishlist wishlist, String username) {
        wishyService.addWishList(wishlist, username);
        return "redirect:/" + username;
    }

    @GetMapping("{username}/{wishListID}/deleteWishList/")
    public String deleteWishList(@PathVariable String username, @PathVariable int wishListID){
        wishyService.deleteWishlist(wishListID);
        return "redirect:/" + username;
    }

    @GetMapping("{username}/see-wishlist/{wishlistid}")
    public String seeWishlist(@PathVariable("username") String username, @PathVariable("wishlistid") int wishlistid, Model model) {
        model.addAttribute("wishlist", wishyService.findAllWishesInWishlist(wishlistid));
        return "see-wishlist";
    }

    @GetMapping("{username}/{wishlistid}/addWish")
    public String addWish(@PathVariable("username") String username, @PathVariable("wishlistid") int wishlistid, Model model) {
        model.addAttribute("wish", new Wish());
        return "add-wish";
    }


    @PostMapping("addWish")
    public String addWish(@ModelAttribute Wish wish, @RequestParam("wishlistid") int wishListID, @RequestParam("username") String username) {
        wishyService.addWish(wish, wishListID);
        return "redirect:/" + username + "/see-wishlist/" + wishListID;
    }

    @GetMapping("{username}/{wishlistid}/{wishId}/deleteWish")
    public String deleteWish(@PathVariable String username, @PathVariable int wishlistid, @PathVariable int wishId) throws SQLException {
        wishyService.deleteWish(wishId);
        return "redirect:/" + username + "/see-wishlist/" + wishlistid;
    }

    @GetMapping("{username}/{wishlistid}/{wishId}/updateWish")
    public String showUpdateWishForm(@PathVariable String username, @PathVariable int wishlistid, @PathVariable int wishId, Model model) {
        Wish wish = wishyService.findWish(wishId);
        if (wish != null) {
            model.addAttribute("wish", wish);
            wishyService.updateWish(wish);
            model.addAttribute("username", username);
            model.addAttribute("wishlistid", wishlistid);
            return "update-wish";
        }
        return "redirect:/" + username + "/see-wishlist/" + wishlistid;
    }

    @PostMapping("updateWish")
    public String updateWish(@ModelAttribute Wish wish, @RequestParam("username") String username, @RequestParam("wishlistid") int wishlistid) {
        wishyService.updateWish(wish);
        return "redirect:/" + username + "/see-wishlist/" + wishlistid;
    }

    @GetMapping("{username}/see-wishlist/{wishlistid}/{wishId}/togglereserve")
    public String reserveWish(@PathVariable String username, @PathVariable int wishlistid, @PathVariable int wishId) {
        if (wishyService.findWish(wishId).isReserved()) {
            wishyService.toggleReserve(false, wishId);
        } else {
            wishyService.toggleReserve(true, wishId);
        }
        return "redirect:/" + username + "/see-wishlist/" + wishlistid;
    }
}
