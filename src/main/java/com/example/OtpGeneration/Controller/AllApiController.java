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


    @PostMapping("/generateotp")
    public BaseResponse generateOTP(@RequestBody CreateUserDTO createUserDTO){
        String response = apiService.generateOTP(createUserDTO.getEmail());
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(response);
        return baseResponse;
    }


    @PostMapping("/validateotp")
    public BaseResponse validateOTP(@RequestBody CreateUserDTO createUserDTO){
        String response = apiService.validateOTP(createUserDTO.getOtp(), createUserDTO.getEmail());
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(response);
        return baseResponse;
    }


    @PostMapping("/resendotp")
    public BaseResponse resendOTP(@RequestBody CreateUserDTO createUserDTO){
        String response = apiService.resendOTP(createUserDTO.getEmail());
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(response);
        return baseResponse;
    }






}
