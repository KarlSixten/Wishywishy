package org.example.wishywishy.controller;

import org.example.wishywishy.model.User;
import org.example.wishywishy.service.WishyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            return "loginsuccess";
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
}
