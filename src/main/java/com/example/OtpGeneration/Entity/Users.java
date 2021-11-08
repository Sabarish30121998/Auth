package com.example.OtpGeneration.Entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private short id;

    @Column(name = "email_id")
    private String email;

    @Column(name = "otp")
    private int otp;

    @Column(name = "is_active", columnDefinition = "TINYINT default 1")
    private byte isActive;

    @Column(name = "deleted_flag", columnDefinition = "TINYINT default 0")
    private byte deletedFlag;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

   // @OneToOne
    @Column(name = "created_by")
    private short creatdBy;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

   // @OneToOne
    @Column(name = "modified_by")
    private short modifiedBy;



}
