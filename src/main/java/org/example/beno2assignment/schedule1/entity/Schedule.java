package org.example.beno2assignment.schedule1.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
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
}
