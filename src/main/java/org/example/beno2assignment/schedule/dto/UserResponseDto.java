package org.example.beno2assignment.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

//레벨 3 구현
@Getter
@AllArgsConstructor
public class UserResponseDto {
    private Long uid;
    private String name;
    private String email;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
}
