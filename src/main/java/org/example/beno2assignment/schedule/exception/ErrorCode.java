package org.example.beno2assignment.schedule.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "없는 데이터에 대한 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "비밀번호가 틀렸습니다."),
    LACK_OF_REQUEST(HttpStatus.BAD_REQUEST, "요청 파라미터가 부족합니다.");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
