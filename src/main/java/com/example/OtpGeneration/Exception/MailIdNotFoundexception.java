package com.example.OtpGeneration.Exception;

import lombok.Data;

@Data
public class MailIdNotFoundexception extends RuntimeException{

    public MailIdNotFoundexception(String messages) {
        super(messages);
    }

}
