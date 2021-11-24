package com.example.OtpGeneration.DTO;

import lombok.Data;

@Data
public class RefreshTokenDTO {
    private String email;
    private String refreshtoken;
}
