package com.example.OtpGeneration.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private short id;

    @Email(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @NotNull
    @Column(name = "email_id")
    private String email;

    @Column(name = "otp")
    private int otp;

    @Column(name = "is_active")
    private byte isActive;

    @Column(name = "deleted_flag")
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


    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "userrole",
            joinColumns = {@JoinColumn(name="fk_users_id",referencedColumnName =  "users_id")},
            inverseJoinColumns = {@JoinColumn(name = "fk_role_id",referencedColumnName = "role_id")}
    )
    private List<Role> listOfRole;


}
