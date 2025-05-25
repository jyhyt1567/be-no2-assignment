package org.example.beno2assignment.schedule.exception;

import lombok.Getter;

//레벨 5 예외 발생 처리 구현
@Getter
public class CustomException extends RuntimeException{
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
