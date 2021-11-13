package com.example.OtpGeneration.ServiceImpl;

import com.example.OtpGeneration.DTO.CreateUserDTO;
import com.example.OtpGeneration.DTO.LoginRequestDTO;
import com.example.OtpGeneration.Entity.Users;
import com.example.OtpGeneration.Repository.UsersRepo;
import com.example.OtpGeneration.Service.ApiService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    JavaMailSender javaMailSender;


    @Override
    public String createUser(CreateUserDTO createUserDTO) {
        try {
            Users users = new Users();
            users.setEmail(createUserDTO.getEmail());
            usersRepo.save(users);
            return "Successfully added to the Coherent Family";
        }
        catch (Exception e)
        {
            throw new RuntimeException("Something went wrong");
        }

    }

    @Override
    public String generateOTP(CreateUserDTO createUserDTO) {
        Optional<Users> users = usersRepo.findByEmail(createUserDTO.getEmail());
        if (users.isPresent()){
            if(users.get().getOtp() == 0){
                Random random = new Random();
                int OTP = 100000 + random.nextInt(900000);
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setFrom("sabarishwaran.manoharan@coherent.in");
                simpleMailMessage.setTo(createUserDTO.getEmail());
                simpleMailMessage.setSubject("Login OTP message");
                simpleMailMessage.setText("OTP number is " + OTP + ". This OTP Number is for Login into the Coherent Pixels Data Keeper Application.");
                users.get().setOtp(OTP);
                usersRepo.save(users.get());
                System.out.println("OTP number is " + OTP + ".");
                System.out.println(" This OTP Number is for Login into the Coherent Pixels Data Keeper Application.");
                javaMailSender.send(simpleMailMessage);
                return "Mail has been successfully sended";
            }
            else {
                throw new RuntimeException("Otp is already generated");
            }
        }else {
            throw new RuntimeException("You are not a Member of Coherent Pixel or Enter a valid email id");
        }
    }


    @Override
    public String resendOTP(CreateUserDTO createUserDTO) {
       Optional<Users> users = usersRepo.findByEmail(createUserDTO.getEmail());
       if (users.isPresent()){
          if(users.get().getOtp() != 0){
            int OTP= users.get().getOtp();
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("sabarishwaran.manoharan@coherent.in");
            simpleMailMessage.setTo(createUserDTO.getEmail());
            simpleMailMessage.setSubject("Login OTP message");
            simpleMailMessage.setText("Resended OTP number is " + OTP + ". This OTP Number is for Login into the Coherent Pixels Data Keeper Application.");
            System.out.println("Resended OTP number is " + OTP + "." );
            System.out.println(" Resended OTP Number is for Login into the Coherent Pixels Data Keeper Application.");
            javaMailSender.send(simpleMailMessage);
            return " Resend OTP (Mail has been successfully sended)";
          }
          else {
            throw new RuntimeException("Otp is already generated");
          }
    }
    else {
       throw new RuntimeException("You are not a Member of Coherent Pixel or enter a valid email id");
    }

}


    @Override
    public String validateOTP(LoginRequestDTO loginRequestDTO) {
        Optional<Users> users = usersRepo.findByEmail(loginRequestDTO.getEmail());
        if (users.isPresent()) {
            if (users.get().getOtp() == loginRequestDTO.getOtp() && users.get().getOtp() !=0) {
                String token = generateToken("SUBJECT", loginRequestDTO.getEmail(), loginRequestDTO.getOtp());
                users.get().setOtp(0);
                usersRepo.save(users.get());
                return token;
            } else {
                throw new RuntimeException("Enter a  valid OTP Number");
            }
        }
        else{
                throw new RuntimeException("You are not a Member of Coherent Pixel or enter a valid email id");
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
