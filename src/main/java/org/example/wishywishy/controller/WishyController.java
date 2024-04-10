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

    User userLoggedIn;

    @GetMapping("test")
    public String getTest() {
        return "test";
    }

    @GetMapping("login")
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


