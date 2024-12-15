package com.hms.controller;

import com.hms.entity.AppUser;
import com.hms.payload.LoginDto;
import com.hms.payload.TokenDto;
import com.hms.repository.AppUserRepository;
import com.hms.service.AppUserService;
//import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private AppUserRepository appUserRepository;
    private AppUserService appUserService;

    public UserController(AppUserRepository appUserRepository, AppUserService appUserService) {
        this.appUserRepository = appUserRepository;
        this.appUserService = appUserService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> CreateUser(
            @RequestBody AppUser user
    ) {
        Optional<AppUser> opUsername =
                appUserRepository.findByUsername(user.getUsername());

        if (opUsername.isPresent()) {//have to be false for proceed further.
            return new ResponseEntity<>("Username already taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<AppUser> opEmail = appUserRepository.findByEmail(user.getEmail());

        if (opEmail.isPresent()) {//have to be false for proceed further.
            return new ResponseEntity<>("Email already taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String encryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(5));
        user.setPassword(encryptedPassword);
        user.setRole("ROLE_USER");
        AppUser savedUser = appUserRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/message")
    public String getMessage() {
        return "hello";
    }

    //http://localhost:8080/login
    @PostMapping("/login")
    public ResponseEntity<?> login(
            //Angular team will use this response basically.
            // in order to this integration in the frontend framework.
            @RequestBody LoginDto dto
    ) {
//        boolean status = appUserService.verifyLogin(dto);
//        if (status) {
//            return new ResponseEntity<>("User logged in", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Invalid username/password", HttpStatus.FORBIDDEN);
//        }

        System.out.println("Hello");

        String token = appUserService.verifyLogin(dto);
        if(token!=null){
            TokenDto tokenDto = new TokenDto();
            tokenDto.setToken(token);
            tokenDto.setType("JWT");
            return new ResponseEntity<>(tokenDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Invalid username/password", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/signup-property-owner")
    public ResponseEntity<?> CreatePropertyOwnerUser(
            @RequestBody AppUser user
    ) {
        Optional<AppUser> opUsername =
                appUserRepository.findByUsername(user.getUsername());

        if (opUsername.isPresent()) {//have to be false for proceed further.
            return new ResponseEntity<>("Username already taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<AppUser> opEmail = appUserRepository.findByEmail(user.getEmail());

        if (opEmail.isPresent()) {//have to be false for proceed further.
            return new ResponseEntity<>("Email already taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String encryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(5));
        user.setPassword(encryptedPassword);
        user.setRole("ROLE_OWNER");
        AppUser savedUser = appUserRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}
/*
@RequestBody this will copy data from JSON to Dto.
 */