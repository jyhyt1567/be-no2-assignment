package org.example.beno2assignment.schedule1And2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ReadScheduleRequestDto {
    private String name;
    private LocalDate modifiedAt;
}
