package org.example.beno2assignment.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ReadScheduleRequestDto {
    private String name;
    private LocalDate modifiedAt;
    private Long uid;
    private Long p;
    private Long pSize;
}
