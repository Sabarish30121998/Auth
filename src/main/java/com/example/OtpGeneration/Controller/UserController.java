package com.example.OtpGeneration.Controller;

import com.example.OtpGeneration.BaseResponse.BaseResponse;
import com.example.OtpGeneration.DTO.LoginRequestDTO;
import com.example.OtpGeneration.Entity.Users;
import com.example.OtpGeneration.Repository.UsersRepo;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/jwt")
public class UserController {

    @Autowired
    UsersRepo usersRepo;

    @PostMapping("/login")
    public BaseResponse login(@RequestBody LoginRequestDTO loginRequestDTO){
        BaseResponse baseResponse = new BaseResponse();
        Optional<Users> users = usersRepo.findByEmail(loginRequestDTO.getEmail());
        if(users.isPresent()){
            if(loginRequestDTO.getOtp()==users.get().getOtp())
            {
                String token = generateToken("subject", users.get().getEmail(), users.get().getOtp());
                baseResponse.setData(token);
            }
            else {
                System.out.println("mail id present.but OTP is wrong  "+ loginRequestDTO.getOtp());
            }
            return baseResponse;
        }
        else {
            throw new RuntimeException("Mail Id is wrong");
        }
    }

    public static String generateToken(String subject,String email, int otp)
    {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setSubject(subject)
                .claim("email",email)
                .claim("OTP",otp)
                .signWith(SignatureAlgorithm.HS256,"secrets")
                .setIssuedAt(now);
        return builder.compact();
    }

}
