package com.example.OtpGeneration.ServiceImpl;

import com.example.OtpGeneration.DTO.LoginRequestDTO;
import com.example.OtpGeneration.DTO.MailDTO;
import com.example.OtpGeneration.Entity.OAuth;
import com.example.OtpGeneration.Entity.Users;
import com.example.OtpGeneration.Exception.MailIdNotFoundexception;
import com.example.OtpGeneration.Repository.OAuthRepo;
import com.example.OtpGeneration.Repository.UsersRepo;
import com.example.OtpGeneration.Service.ApiService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    JavaMailSender javaMailSender;


    @Override
    public Object createUser(MailDTO mailDTO) {
        try {
            Users users = new Users();
            users.setEmail(mailDTO.getEmail());
            usersRepo.save(users);
            return users;
        }
        catch (Exception e)
        {
            throw new RuntimeException("Something went wrong Account is not Created");
        }
    }

    @Override
    public String generateOTP(MailDTO mailDTO) {
        Optional<Users> users = usersRepo.findByEmail(mailDTO.getEmail());
        if (users.isPresent()){
            if(users.get().getOtp() == 0){
                Random random = new Random();
                int OTP = 100000 + random.nextInt(900000);
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setFrom("sabarishwaran.manoharan@coherent.in");
                simpleMailMessage.setTo(mailDTO.getEmail());
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
            throw new MailIdNotFoundexception("You are not a Member of Coherent Pixel or Enter a valid email id");
        }
    }


    @Override
    public String resendOTP(MailDTO mailDTO) {
       Optional<Users> users = usersRepo.findByEmail(mailDTO.getEmail());
       if (users.isPresent()){
          if(users.get().getOtp() != 0){
            int OTP= users.get().getOtp();
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("sabarishwaran.manoharan@coherent.in");
            simpleMailMessage.setTo(mailDTO.getEmail());
            simpleMailMessage.setSubject("Login OTP message");
            simpleMailMessage.setText("Resended OTP number is " + OTP + ". This OTP Number is for Login into the Coherent Pixels Data Keeper Application.");
            System.out.println("Resended OTP number is " + OTP + "." );
            System.out.println(" Resended OTP Number is for Login into the Coherent Pixels Data Keeper Application.");
            javaMailSender.send(simpleMailMessage);
            return " Resend OTP (Mail has been successfully sended)";
          }
          else {
            throw new RuntimeException("Please Generate a OTP");
          }
    }
    else {
       throw new MailIdNotFoundexception("You are not a Member of Coherent Pixel or enter a valid email id");
    }

}

    @Autowired
    OAuthRepo oAuthRepo;

    @Override
    public String validateOTP(LoginRequestDTO loginRequestDTO) {
        Optional<Users> users = usersRepo.findByEmail(loginRequestDTO.getEmail());
        if (users.isPresent()) {
            if(users.get().getOtp() != 0){
              if (users.get().getOtp() == loginRequestDTO.getOtp()) {
                String token = generateToken("SUBJECT", loginRequestDTO.getEmail(), loginRequestDTO.getOtp());
                OAuth oAuth = new OAuth();
                oAuth.setAccessToken(token);
                oAuth.setRefreshToken(token);
                oAuth.setUserIdFk(users.get().getId());
                oAuthRepo.save(oAuth);
                users.get().setOtp(0);
                usersRepo.save(users.get());
                return token;
              }
              else {
                throw new RuntimeException("Enter a  valid OTP Number");
              }
            }
            else {
                throw new RuntimeException("Generated a Otp number");
            }
        }
        else{
                throw new MailIdNotFoundexception("You are not a Member of Coherent Pixel or enter a valid email id");
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



    public UserDetails loadByEmail(String email) {
        Optional<Users> users = usersRepo.findByEmail(email);
        if(users == null){
               throw new RuntimeException("email not found");
        }
        String otpCode = Integer.toString(users.get().getOtp());
        return new org.springframework.security.core.userdetails.User(users.get().getEmail(),otpCode,getAuthorities());

    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
        return list;
    }
}
