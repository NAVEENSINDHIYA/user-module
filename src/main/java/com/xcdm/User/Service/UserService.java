package com.xcdm.User.Service;


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(RegistrationRequest registrationRequest) {
        // Implement registration logic here
        // Check if the email is already registered
        if (userRepository.findByEmail(registrationRequest.getEmail()) != null) {
            throw new EmailAlreadyExistsException("Email already registered");
        }

        // Hash and salt the password
        String hashedPassword = passwordEncoder.encode(registrationRequest.getPassword());

        // Create a new user
        User user = new User();
        user.setFullName(registrationRequest.getFullName());
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(hashedPassword);
        user.setEmailVerified(false);

        // Save the user to the database
        return userRepository.save(user);
    }

    public User loginUser(LoginRequest loginRequest) {
        // Implement login logic here
        // Find the user by email
        User user = userRepository.findByEmail(loginRequest.getEmail());

        // Verify the provided password
        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return user; // Successful login
        }

        return null; // Authentication failed
    }

    // Additional methods for user-related business logic
}

