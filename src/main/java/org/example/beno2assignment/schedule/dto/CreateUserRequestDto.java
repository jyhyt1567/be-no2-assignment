package org.example.beno2assignment.schedule.dto;

import lombok.Getter;

@Getter
public class CreateUserRequestDto {
    private String name;
    private String password;
    private String email;
}
