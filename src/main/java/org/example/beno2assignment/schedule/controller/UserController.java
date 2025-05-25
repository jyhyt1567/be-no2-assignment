package org.example.beno2assignment.schedule.controller;

import jakarta.validation.Valid;
import org.example.beno2assignment.schedule.dto.CreateUserRequestDto;
import org.example.beno2assignment.schedule.dto.UserResponseDto;
import org.example.beno2assignment.schedule.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> signUp(@RequestBody @Valid CreateUserRequestDto requestDto){
        return new ResponseEntity<>(userService.signUp(requestDto), HttpStatus.CREATED);
    }
}
