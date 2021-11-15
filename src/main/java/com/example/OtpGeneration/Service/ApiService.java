package com.example.OtpGeneration.Service;

import com.example.OtpGeneration.DTO.CreateUserDTOs;
import com.example.OtpGeneration.DTO.LoginRequestDTO;
import com.example.OtpGeneration.DTO.MailDTO;
import com.example.OtpGeneration.Entity.Users;
import org.springframework.stereotype.Service;

@Service
public interface ApiService {
    Object createUser(CreateUserDTOs createUserDTOs);

    String generateOTP(MailDTO mailDTO);

    String resendOTP(MailDTO mailDTO);

    String validateOTP(LoginRequestDTO loginRequestDTO);
}
