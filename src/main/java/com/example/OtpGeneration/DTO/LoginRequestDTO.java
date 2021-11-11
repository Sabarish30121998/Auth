package com.example.OtpGeneration.DTO;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private int otp;
}
