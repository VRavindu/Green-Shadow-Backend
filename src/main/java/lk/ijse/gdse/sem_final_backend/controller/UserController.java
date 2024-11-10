package lk.ijse.gdse.sem_final_backend.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse.sem_final_backend.dto.impl.UserDTO;
import lk.ijse.gdse.sem_final_backend.exception.AlreadyExistsException;
import lk.ijse.gdse.sem_final_backend.exception.DataPersistFailedException;
import lk.ijse.gdse.sem_final_backend.service.UserService;
import lk.ijse.gdse.sem_final_backend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final Mapping mapping;

    @PostMapping
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserDTO user) {
        try {
            userService.saveUser(mapping.convertUserDTOToUser(user));
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (AlreadyExistsException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (DataPersistFailedException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
