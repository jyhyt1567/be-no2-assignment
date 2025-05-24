package org.example.beno2assignment.schedule.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class CustomErrorResponse{
    private HttpStatus httpStatus;
    private String message;
}
