package com.example.userservicemay25.controllers;

import com.example.userservicemay25.dtos.*;

import com.example.userservicemay25.models.User;

import com.example.userservicemay25.services.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController{
    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto requestDto) {
        return null;
    }

    @PostMapping("/signup")
    public UserDto signUp(@RequestBody SignUpRequestDto requestDto) {
        User user = userService.signUp(
                requestDto.getName(),
                requestDto.getEmail(),
                requestDto.getPassword()
        );
        // Converting User to UserDto
        System.out.println("Created user: " + user.getName() + ", " + user.getEmail());
        return UserDto.from(user);

    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logOut(@RequestBody LogoutRequestDto requestDto) {
        return null;
    }

    @GetMapping("/validate/{token}")
    public ResponseEntity<UserDto> validateToken(@PathVariable("token") String tokenValue) {
    return null;
    }
}


