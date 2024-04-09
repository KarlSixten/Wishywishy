package org.example.wishywishy.controller;

import org.example.wishywishy.model.Wish;
import org.example.wishywishy.service.WishyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

   @GetMapping("{name}/delete")
    public String deleteWish(@PathVariable("name") int wishId) throws SQLException {
        Wish wishToDelete = wishyService.findWish(wishId);
        int wishID = wishToDelete.getWishID();
        wishyService.deleteWish(wishID);
        return "redirect:/test";
    }
}
