package org.example.beno2assignment.schedule1And2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.beno2assignment.schedule1And2.dto.ScheduleResponseDto;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {
    private Long id;
    private String name;
    private String todo;
    private String password;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public Schedule(String name, String todo, String password) {
        this.name = name;
        this.todo = todo;
        this.password = password;
    }
    public ScheduleResponseDto toResponseDto(){
        return new ScheduleResponseDto(this.getId(), this.getName(), this.getTodo(), this.getCreateAt(), this.getModifiedAt());
    }
}
