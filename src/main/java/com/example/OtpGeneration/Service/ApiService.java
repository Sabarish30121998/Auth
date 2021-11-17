package com.example.OtpGeneration.Service;

import com.example.OtpGeneration.DTO.LoginRequestDTO;
import com.example.OtpGeneration.DTO.MailDTO;
import org.springframework.stereotype.Service;

@Service
public interface ApiService {
    Object createUser(MailDTO mailDTO);

    String generateOTP(MailDTO mailDTO);

    String resendOTP(MailDTO mailDTO);

    String validateOTP(LoginRequestDTO loginRequestDTO);
}
