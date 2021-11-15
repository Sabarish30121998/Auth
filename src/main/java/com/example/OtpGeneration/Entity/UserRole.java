package com.example.OtpGeneration.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "userrole")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id")
    private short userRoleId;

    @OneToOne
    @JoinColumn(name = "fk_users_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "fk_role_id")
    private Role role;
}
