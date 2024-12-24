package com.bookmyshow.services;

import com.bookmyshow.models.User;

public interface IUserService {
    public User registerUser(String name, String email, String password);
    public void loginUser(String email, String password);
    public void logoutUser();
}
