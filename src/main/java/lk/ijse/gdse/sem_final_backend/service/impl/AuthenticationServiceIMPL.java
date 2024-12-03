package lk.ijse.gdse.sem_final_backend.service.impl;

import lk.ijse.gdse.sem_final_backend.dto.impl.UserDTO;
import lk.ijse.gdse.sem_final_backend.entity.User;
import lk.ijse.gdse.sem_final_backend.exception.AlreadyExistsException;
import lk.ijse.gdse.sem_final_backend.exception.DataPersistFailedException;
import lk.ijse.gdse.sem_final_backend.jwtModels.JwtAuthResponse;
import lk.ijse.gdse.sem_final_backend.jwtModels.SignIn;
import lk.ijse.gdse.sem_final_backend.repository.UserRepository;
import lk.ijse.gdse.sem_final_backend.service.AuthenticationService;
import lk.ijse.gdse.sem_final_backend.service.JWTService;
import lk.ijse.gdse.sem_final_backend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceIMPL implements AuthenticationService {
    private final UserRepository userService;
    private final JWTService jwtService;
    private final Mapping mapping;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthResponse signIn(SignIn signIn) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signIn.getEmail(),signIn.getPassword()));
        var userByEmail = userService.findByEmail(signIn.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var generatedToken = jwtService.generateToken(userByEmail);
        return JwtAuthResponse.builder().token(generatedToken).build() ;
    }
    @Override
    public JwtAuthResponse signUp(UserDTO signUp) {
        Optional<User> existsUser = userService.findByEmail(signUp.getEmail());
        if (!existsUser.isPresent()){
            User savedUser = userService.save(mapping.convertUserDTOToUser(signUp));
            if (savedUser == null){
                throw new DataPersistFailedException("User Save Failed");
            }
            var genToken = jwtService.generateToken(savedUser);
            return JwtAuthResponse.builder().token(genToken).build();
        }else {
            throw new AlreadyExistsException("User already exists");
        }
    }
    @Override
    public JwtAuthResponse refreshToken(String accessToken) {
        var userName = jwtService.extractUsername(accessToken);
        var userEntity =
                userService.findByEmail(userName).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var refreshToken = jwtService.refreshToken(userEntity);
        return JwtAuthResponse.builder().token(refreshToken).build();
    }
}