package com.example.OtpGeneration.Exception;

import com.example.OtpGeneration.BaseResponse.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class CustomException {

    @ExceptionHandler
    private ResponseEntity mailIdNotFoundexception(MailIdNotFoundexception e) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        baseResponse.setStatusMessage("UNAUTHORIZED");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(baseResponse);
    }

}
