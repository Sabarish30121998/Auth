package com.example.OtpGeneration.ServiceImpl;

import com.example.OtpGeneration.DTO.CreateUserDTO;
import com.example.OtpGeneration.Entity.Users;
import com.example.OtpGeneration.Repository.UsersRepo;
import com.example.OtpGeneration.Service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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
        Users users = new Users();
        users.setEmail(createUserDTO.getEmail());
        usersRepo.save(users);
        return "Successfully added to the Coherent Family";
    }

    @Override
    public String generateOTP(String email) {
        Optional<Users> users = usersRepo.findByEmail(email);
        if (users.isPresent() && users.get().getOtp() == 0) {
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
        return "Sorry You are Not a Member of Coherent Pixel or The OTP is already send to your EMail";
    }

    @Override
    public String validateOTP(int mailotp, String email) {
        Optional<Users> users = usersRepo.findByEmail(email);
        if(users.isPresent() && users.get().getOtp()==mailotp){
            users.get().setOtp(0);
            usersRepo.save(users.get());
            System.out.println("successfully logged into the Coherent data keeper Application");
          //  return "successfully logged into the Coherent data keeper Application ";
            return users.get().getEmail();
        }
        System.out.println("Please Enter a valid OTP Number");
        return "Please Enter a valid OTP Number";
    }

    @Override
    public String resendOTP(String email) {
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
        return "enter a valid mail id  ";
    }


}
