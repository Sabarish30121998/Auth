package com.example.OtpGeneration.Controller;

import com.example.OtpGeneration.BaseResponse.BaseResponse;
import com.example.OtpGeneration.DTO.CreateUserDTO;
import com.example.OtpGeneration.Entity.Users;
import com.example.OtpGeneration.Service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class AllApiController {

    @Autowired
    ApiService apiService;

    @PostMapping("/createuser")
    public BaseResponse createuser(@RequestBody CreateUserDTO createUserDTO) {
        String response = apiService.createUser(createUserDTO);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(response);
        return baseResponse;
    }


    @PostMapping("/generateotp/{email}")
    public BaseResponse generateOTP(@PathVariable String email){
        String response = apiService.generateOTP(email);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(response);
        return baseResponse;
    }


    @GetMapping("/validateotp/{mailotp}/{email}")
    public BaseResponse validateOTP(@PathVariable int mailotp,@PathVariable String email){
        String response = apiService.validateOTP(mailotp,email);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(response);
        return baseResponse;
    }


    @PostMapping("/resendotp/{email}")
    public BaseResponse resendOTP(@PathVariable String email){
        String response = apiService.resendOTP(email);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(response);
        return baseResponse;
    }



}
