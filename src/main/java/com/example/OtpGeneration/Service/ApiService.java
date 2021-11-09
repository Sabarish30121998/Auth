package com.example.OtpGeneration.Service;

import com.example.OtpGeneration.DTO.CreateUserDTO;
import com.example.OtpGeneration.Entity.Users;
import org.springframework.stereotype.Service;

@Service
public interface ApiService {
    String createUser(CreateUserDTO createUserDTO);

    String generateOTP(String email);

    String validateOTP(int mailotp, String email);

    String resendOTP(String email);
}
