package org.example.beno2assignment.schedule.service;

import org.example.beno2assignment.schedule.dto.CreateUserRequestDto;
import org.example.beno2assignment.schedule.dto.UserResponseDto;

//레벨 3 구현
public interface UserService {
    UserResponseDto signUp(CreateUserRequestDto requestDto);
    UserResponseDto findUserByUid(Long uid);
}
