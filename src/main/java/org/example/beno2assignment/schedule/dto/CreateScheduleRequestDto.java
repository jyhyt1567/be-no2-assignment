package org.example.beno2assignment.schedule.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {
    private String todo;
    private String name;
    private String password;
    private Long uid;
}
