package lk.ijse.gdse.sem_final_backend.service;

import lk.ijse.gdse.sem_final_backend.dto.impl.UserDTO;
import lk.ijse.gdse.sem_final_backend.jwtModels.JwtAuthResponse;
import lk.ijse.gdse.sem_final_backend.jwtModels.SignIn;

public interface AuthenticationService {
    JwtAuthResponse signIn(SignIn signIn);
    JwtAuthResponse signUp(UserDTO signUp);
    JwtAuthResponse refreshToken(String accessToken);
}