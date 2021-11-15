package com.example.OtpGeneration.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private short roleId;

    @Column(name = "role_name")
    private String roleName;
}
