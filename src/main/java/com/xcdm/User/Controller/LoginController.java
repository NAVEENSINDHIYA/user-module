package com.xcdm.User.Controller;

import com.xcdm.User.Dto.LoginRequest;
import com.xcdm.User.Entity.User;
import com.xcdm.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody @Validated LoginRequest loginRequest) {
        User user = userService.loginUser(loginRequest);
        if (user != null) {
            // Generate and return a JWT token (not shown in this example)
            return ResponseEntity.ok("Login successful. Token: <JWT_TOKEN>");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}

