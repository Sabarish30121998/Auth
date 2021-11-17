package com.example.OtpGeneration.Controller;

import com.example.OtpGeneration.BaseResponse.BaseResponse;
import com.example.OtpGeneration.DTO.LoginRequestDTO;
import com.example.OtpGeneration.DTO.MailDTO;
import com.example.OtpGeneration.Service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cdk-auth-service/user")
public class AllApiController {

    @Autowired
    ApiService apiService;

    @PostMapping("/createuser")
    public BaseResponse createuser(@RequestBody MailDTO mailDTO) {
        Object response = apiService.createUser(mailDTO);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(response);
        return baseResponse;
    }


    @PostMapping("/generateotp")
    public BaseResponse generateOTP(@RequestBody MailDTO mailDTO){
        String response = apiService.generateOTP(mailDTO);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(response);
        return baseResponse;
    }


    @PostMapping("/resendotp")
    public BaseResponse resendOTP(@RequestBody MailDTO mailDTO){
        String response = apiService.resendOTP(mailDTO);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(response);
        return baseResponse;
    }


    @PostMapping("/validateotp")
    public BaseResponse validateOTP(@RequestBody LoginRequestDTO loginRequestDTO){
        String response = apiService.validateOTP(loginRequestDTO);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(response);
        return baseResponse;
    }

    @GetMapping("/summa")
    public  String summa(){
        return "summa with USER";
    }

}