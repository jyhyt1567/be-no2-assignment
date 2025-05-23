package org.example.beno2assignment.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.beno2assignment.schedule.dto.UserResponseDto;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class User {
    private Long uid;
    private String name;
    private String password;
    private String email;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public User(String name, String password, String email){
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public UserResponseDto toResponseDto(){
        return new UserResponseDto(this.getUid(), this.getName(), this.getEmail(), this.getCreateAt(), this.getModifiedAt());
    }
}
