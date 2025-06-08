package com.example.userservicemay25.services;

import com.example.userservicemay25.models.Token;
import com.example.userservicemay25.models.User;
import com.example.userservicemay25.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Token login(String email, String password) {
        return null;
    }

    @Override
    public User signUp(String name, String email, String password) {

        // check if the user already exits
        // If the user exits --> return the exiting user object
        Optional<User> userOptional = userRepository.findByEmail(email);
//        if(userOptional.isPresent()){
//            return userOptional.get();
//        }

        User user= new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        return userRepository.save(user);


        // Else create a new user with the details and return the user object

    }

    @Override
    public User validateToken(String Token) {
        return null;
    }


    @Override
    public void logout(String tokenValue) {

    }


}
