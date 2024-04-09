package org.example.wishywishy.service;

import org.example.wishywishy.model.User;
import org.example.wishywishy.repository.WishyRepository;
import org.springframework.stereotype.Service;

@Service
public class WishyService {
    private final WishyRepository wishyRepository;

    public WishyService(WishyRepository wishyRepository) {
        this.wishyRepository = wishyRepository;
    }

    public User createUser(User newUser) {
        return wishyRepository.createUser(newUser);
    }

    public User checkIfLoginValid(User userToCheck) {
        return wishyRepository.checkIfLoginValid(userToCheck);
    }
}
