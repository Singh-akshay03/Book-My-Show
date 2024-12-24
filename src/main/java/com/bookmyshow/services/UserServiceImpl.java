package com.bookmyshow.services;

import com.bookmyshow.models.User;
import com.bookmyshow.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User registerUser(String name, String email, String password) {

        User user = new User();
        user.setEmail(email);
        user.setUsername(name);
        user.setPassword(password);
        return userRepository.save(user);
    }

    @Override
    public void loginUser(String email, String password) {

    }

    @Override
    public void logoutUser() {

    }
}
