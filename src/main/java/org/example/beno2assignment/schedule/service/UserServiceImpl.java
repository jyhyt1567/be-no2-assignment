package org.example.beno2assignment.schedule.service;

import org.example.beno2assignment.schedule.dto.CreateUserRequestDto;
import org.example.beno2assignment.schedule.dto.UserResponseDto;
import org.example.beno2assignment.schedule.entity.User;
import org.example.beno2assignment.schedule.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto signUp(CreateUserRequestDto requestDto) {

        User user = new User(requestDto.getName(), requestDto.getPassword(), requestDto.getEmail());

        return userRepository.signUp(user).toResponseDto();
    }

    @Override
    public UserResponseDto findUserByUid(Long uid) {
        return userRepository.findUserByUidorElseThrow(uid).toResponseDto();
    }
}
