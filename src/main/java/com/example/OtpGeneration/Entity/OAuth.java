package com.example.OtpGeneration.Entity;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "oauth_token_detail")
public class OAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private short id;

    @Column(name = "access_token")
    private String accessToken;

  //  @OneToOne
    @Column(name = "user_id_fk")
    private short userIdFk;

    @Column(name = "refresh_token")
    private int refreshToken;

    @Column(name = "tot_attempt")
    private short totAttempt;

    @Column(name = "deleted_flag")
    private byte deletedFlag;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;


}
