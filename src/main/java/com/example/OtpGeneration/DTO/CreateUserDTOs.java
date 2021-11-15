package com.example.OtpGeneration.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CreateUserDTOs {
    private String email;
    private List<RoleDTO> userRole;
}
