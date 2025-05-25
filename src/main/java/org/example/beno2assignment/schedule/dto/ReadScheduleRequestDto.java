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

    //레벨 4 페이지네이션 구현
    private Long p;
    private Long pSize;
}
