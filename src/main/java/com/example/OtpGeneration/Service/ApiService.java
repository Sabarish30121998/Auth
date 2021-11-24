package com.example.OtpGeneration.Service;

import com.example.OtpGeneration.DTO.LoginRequestDTO;
import com.example.OtpGeneration.DTO.MailDTO;
import com.example.OtpGeneration.DTO.RefreshTokenDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApiService {
    Object createUser(MailDTO mailDTO);

    String generateOTP(MailDTO mailDTO);

    String resendOTP(MailDTO mailDTO);

    Object validateOTP(LoginRequestDTO loginRequestDTO);

    Object refreshedToken(RefreshTokenDTO refreshTokenDTO);
}
