package com.example.OtpGeneration.Exception;

import com.example.OtpGeneration.BaseResponse.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler
    private ResponseEntity andexception(MailIdNotFoundexception e) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        baseResponse.setStatusMessage("Mail ID Not Found Exception");
        baseResponse.setData(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(baseResponse);
    }


    @ExceptionHandler
    public ResponseEntity exec(Exception e)
    {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(500);
        baseResponse.setStatusMessage("Its a General Exception");
        baseResponse.setData(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(baseResponse);
    }

}
