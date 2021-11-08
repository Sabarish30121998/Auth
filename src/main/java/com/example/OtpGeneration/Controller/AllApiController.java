package com.example.OtpGeneration.Controller;

import com.example.OtpGeneration.BaseResponse.BaseResponse;
import com.example.OtpGeneration.DTO.CreateUserDTO;
import com.example.OtpGeneration.Entity.Users;
import com.example.OtpGeneration.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("user")
public class AllApiController {

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    JavaMailSender javaMailSender;

    @PostMapping("/createuser")
    public BaseResponse createuser(@RequestBody CreateUserDTO createUserDTO) {
        Users users = new Users();
        users.setEmail(createUserDTO.getEmail());
        usersRepo.save(users);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(users);
        return baseResponse;
    }

    @PostMapping("/generateotp/{email}")
    public String generateOTP(@PathVariable String email){
        Optional<Users> users = usersRepo.findByEmail(email);
        if (users.isPresent()) {
            Random random = new Random();
            int OTP = 100000 + random.nextInt(900000);

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("sabarishwaran.manoharan@coherent.in");
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject("Login OTP message");
            simpleMailMessage.setText("OTP number is " + OTP + ". This OTP Number is for Login into the Coherent Pixels Data Keeper Application.");

            users.get().setOtp(OTP);
            usersRepo.save(users.get());

            System.out.println("OTP number is " + OTP + "." );
            System.out.println(" This OTP Number is for Login into the Coherent Pixels Data Keeper Application.");

            javaMailSender.send(simpleMailMessage);
            return "Mail has been successfully sended";
        }
        return "Sorry You are Not a Member of Coherent Pixel";
    }


    @GetMapping("/validateotp/{mailotp}/{email}")
    public String validateOTP(@PathVariable int mailotp,@PathVariable String email){
        Optional<Users> users = usersRepo.findByEmail(email);
        if(users.isPresent() && users.get().getOtp()==mailotp){
                users.get().setOtp(0);
                usersRepo.save(users.get());
                return "successfully logged into the Coherent data keeper Application";
        }
        System.out.println("Please Enter a valid OTP Number");
        return "Please Enter a valid OTP Number";
    }


    @PostMapping("/resendotp/{email}")
    public String resendOTP(@PathVariable String email){
        Optional<Users> users = usersRepo.findByEmail(email);
        if (users.isPresent() && users.get().getOtp() !=0){
           int OTP= users.get().getOtp();

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("sabarishwaran.manoharan@coherent.in");
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject("Login OTP message");
            simpleMailMessage.setText("Resended OTP number is " + OTP + ". This OTP Number is for Login into the Coherent Pixels Data Keeper Application.");

            System.out.println("Resended OTP number is " + OTP + "." );
            System.out.println(" Resended OTP Number is for Login into the Coherent Pixels Data Keeper Application.");

            javaMailSender.send(simpleMailMessage);
            return " Resend OTP (Mail has been successfully sended)";

        }
        return "enter a valid mail id";
    }



}
