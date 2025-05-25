package org.example.beno2assignment.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class CreateScheduleRequestDto {

    //레벨 6 null 체크 및 특정 패턴에 대한 검증 수행 구현
    @NotBlank(message = "할 일을 작성해 주세요.")
    @Length(max=200, message = "할 일은 최대 200자 까지 가능합니다.")
    private String todo;

    private String name;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotNull(message = "아이디를 작성해 주세요.")
    private Long uid;
}
