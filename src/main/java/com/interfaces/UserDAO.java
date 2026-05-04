package com.interfaces;

import com.model.User;
import java.util.List;

public interface UserDAO {
    boolean registerUser(User user);
    User getUserByEmail(String email);
    boolean approveUser(int userId);
    List<User> getAllUsers();          // admin
    boolean deleteUser(int userId);   // admin
}