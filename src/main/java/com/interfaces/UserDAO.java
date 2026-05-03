package com.interfaces;

import com.model.User;

public interface UserDAO {
    
    boolean registerUser(User user);
    User getUserByEmail(String email);
    boolean approveUser(int userId);
}