package org.example.beno2assignment.schedule.service;

import org.example.beno2assignment.schedule.dto.CreateUserRequestDto;
import org.example.beno2assignment.schedule.dto.UserResponseDto;

public interface UserService {
    UserResponseDto signUp(CreateUserRequestDto requestDto);
    UserResponseDto findUserByUid(Long uid);
}
