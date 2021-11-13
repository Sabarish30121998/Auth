package com.example.OtpGeneration.Service;

import com.example.OtpGeneration.DTO.CreateUserDTO;
import com.example.OtpGeneration.DTO.LoginRequestDTO;
import com.example.OtpGeneration.Entity.Users;
import org.springframework.stereotype.Service;

@Service
public interface ApiService {
    String createUser(CreateUserDTO createUserDTO);

    String generateOTP(CreateUserDTO createUserDTO);

    String resendOTP(CreateUserDTO createUserDTO);

    String validateOTP(LoginRequestDTO loginRequestDTO);
}
