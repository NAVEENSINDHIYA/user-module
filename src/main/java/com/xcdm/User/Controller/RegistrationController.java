package com.xcdm.User.Controller;

import com.xcdm.User.Dto.RegistrationRequest;
import com.xcdm.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody @Validated RegistrationRequest registrationRequest) {
        User registeredUser = userService.registerUser(registrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registration successful");
    }
}

