package org.example.wishywishy.controller;

import org.example.wishywishy.WishyWishyApplication;
import org.example.wishywishy.service.WishyService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("")
public class WishyController {
    private final WishyService wishyService;

    public WishyController(WishyService wishyService, ApplicationArguments springApplicationArguments, WishyWishyApplication wishyWishyApplication) {
        this.wishyService = wishyService;
    }

    @GetMapping("test")
    public String getTest() throws IOException {
        return "test";
    }
}
