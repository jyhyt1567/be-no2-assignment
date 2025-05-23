package org.example.beno2assignment.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.beno2assignment.schedule.dto.ScheduleResponseDto;

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
    private Long uid;

    public Schedule(String name, String todo, String password, Long uid) {
        this.name = name;
        this.todo = todo;
        this.password = password;
        this.uid = uid;
    }
    public ScheduleResponseDto toResponseDto(){
        return new ScheduleResponseDto(this.getId(), this.getName(), this.getTodo(), this.getCreateAt(), this.getModifiedAt(), this.getUid());
    }
}
