package lk.ijse.gdse.sem_final_backend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.sem_final_backend.entity.User;
import lk.ijse.gdse.sem_final_backend.repository.UserRepository;
import lk.ijse.gdse.sem_final_backend.service.UserService;
import lk.ijse.gdse.sem_final_backend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {
    private final UserRepository userRepository;
    private final Mapping mapping;

    @Override
    public void saveUser(User user) {
        Optional<User> existsUser = userRepository.findByEmail(user.getEmail());
        if (!existsUser.isPresent()) {
            User save = userRepository.save(user);
            if (save == null) {
                throw new RuntimeException("User not Saved");
            }
        }else {
            throw new RuntimeException("User already exists");
        }
    }
}
