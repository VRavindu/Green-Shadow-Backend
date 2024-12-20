package lk.ijse.gdse.sem_final_backend.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse.sem_final_backend.dto.impl.UserDTO;
import lk.ijse.gdse.sem_final_backend.exception.AlreadyExistsException;
import lk.ijse.gdse.sem_final_backend.exception.DataPersistFailedException;
import lk.ijse.gdse.sem_final_backend.jwtModels.JwtAuthResponse;
import lk.ijse.gdse.sem_final_backend.jwtModels.SignIn;
import lk.ijse.gdse.sem_final_backend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/auth/")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @PostMapping(value = "signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtAuthResponse> signUp(@Valid @RequestBody UserDTO user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            JwtAuthResponse jwtAuthResponse = authenticationService.signUp(user);
            if (jwtAuthResponse != null){
                return new ResponseEntity<>(jwtAuthResponse, HttpStatus.CREATED);
            } else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (AlreadyExistsException e) {
            logger.warn("User already exists with email: {}", user.getEmail());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DataPersistFailedException e) {
            logger.error("Failed to persist user with email: {}", user.getEmail(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "signin")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SignIn signIn) {
        return ResponseEntity.ok(authenticationService.signIn(signIn));
    }
    @PostMapping("refresh")
    public ResponseEntity<JwtAuthResponse> refreshToken (@RequestParam ("refreshToken") String refreshToken) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }
}
