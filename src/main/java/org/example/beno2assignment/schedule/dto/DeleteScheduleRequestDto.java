package org.example.beno2assignment.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DeleteScheduleRequestDto {

    //레벨 6 null 체크 및 특정 패턴에 대한 검증 수행 구현

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
