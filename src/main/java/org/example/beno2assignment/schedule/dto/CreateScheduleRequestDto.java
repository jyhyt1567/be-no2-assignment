package org.example.beno2assignment.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class CreateScheduleRequestDto {

    @NotBlank(message = "할 일을 작성해 주세요.")
    @Length(max=200, message = "할 일은 최대 200자 까지 가능합니다.")
    private String todo;

    private String name;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    private Long uid;
}
