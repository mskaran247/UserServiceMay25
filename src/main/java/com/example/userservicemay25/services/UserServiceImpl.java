package com.example.userservicemay25.services;

import com.example.userservicemay25.models.Token;
import com.example.userservicemay25.models.User;
import com.example.userservicemay25.repositories.TokenRepository;
import com.example.userservicemay25.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenRepository tokenRepository;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
    }

    // ***************************************************************** //

    @Override
    public Token login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isEmpty()){
            // Throw an exception here that the user does not exit
            // redirect to the signup page
            return null;
        }

        User user = userOptional.get();
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
            // Throw an exception for an invalid password
            return null;
        }
        Token token = new Token();
        token.setValue(RandomStringUtils.randomAlphanumeric(128));
        token.setUser(user);

        // We want to expire our token in 30 days
        LocalDateTime thirtyDaysFromNow = LocalDateTime.now().plusDays(30);
        token.setExpiryDateTime(thirtyDaysFromNow);

        return tokenRepository.save(token);
    }

    // ********************************************************************//

    @Override
    public User signUp(String name, String email, String password) {

        // check if the user already exits
        // If the user exits --> return the exiting user object
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            return userOptional.get();
        }

        User user= new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        return userRepository.save(user);

        // Else create a new user with the details and return the user object
    }
    // ************************************************************** //


    @Override
    public User validateToken(String tokenValue) {
        // token exits in the token table
        // need to check that the token is not expired
        // tokenExpiryDate > currentDate
        Optional<Token> tokenOptional = tokenRepository.findByValueAndExpiryDateTimeGreaterThan(tokenValue, LocalDateTime.now());
        if(tokenOptional.isEmpty()){
            return null;
        }
        return tokenOptional.get().getUser();
    }


    // ******************************************************************** //

    @Override
    public void logout(String tokenValue) {
        // Set the expiry date of the token to now and invalidating the token


    }


}
