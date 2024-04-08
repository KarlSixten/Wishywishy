package org.example.wishywishy.service;

import org.example.wishywishy.repository.WishyRepository;
import org.springframework.stereotype.Service;

@Service
public class WishyService {
    private final WishyRepository wishyRepository;

    public WishyService(WishyRepository wishyRepository) {
        this.wishyRepository = wishyRepository;
    }
}
