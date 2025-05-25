package org.example.beno2assignment.schedule.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

//레벨 5 예외 발생 처리 구현
@Getter
@AllArgsConstructor
public class CustomErrorResponse{
    private HttpStatus httpStatus;
    private String message;
}
