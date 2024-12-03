package lk.ijse.gdse.sem_final_backend.service;

import lk.ijse.gdse.sem_final_backend.customObj.UserResponse;
import lk.ijse.gdse.sem_final_backend.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    void saveUser(User user);
    UserResponse getUserByEmail(String email);
    void updateUser(User user);
    UserDetailsService userDetailsService();
}
