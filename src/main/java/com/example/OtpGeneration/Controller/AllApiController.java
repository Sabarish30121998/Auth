package com.example.OtpGeneration.Controller;

import com.example.OtpGeneration.BaseResponse.BaseResponse;
import com.example.OtpGeneration.DTO.LoginRequestDTO;
import com.example.OtpGeneration.DTO.MailDTO;
import com.example.OtpGeneration.DTO.RefreshTokenDTO;
import com.example.OtpGeneration.Service.ApiService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
        Object response = apiService.validateOTP(loginRequestDTO);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(response);
        return baseResponse;
    }


    @PostMapping("/refreshtoken")
    public BaseResponse refreshTokens(@RequestBody RefreshTokenDTO refreshTokenDTO){
        Object response = apiService.refreshedToken(refreshTokenDTO);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(response);
        return baseResponse;
    }

    @GetMapping("/summa")
    public  String summa(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String freshtoken=token.substring(7,token.length());
        Claims claims = Jwts.parser().setSigningKey("secrets").parseClaimsJws(freshtoken).getBody();
        String Email = String.valueOf(claims.get("email"));
        return Email;
    }

}