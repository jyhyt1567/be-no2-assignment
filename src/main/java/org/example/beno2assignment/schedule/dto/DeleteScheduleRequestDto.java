package org.example.beno2assignment.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DeleteScheduleRequestDto {

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
