package org.example.beno2assignment.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long id;
    private String todo;
    private String name;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private Long uid;
}
