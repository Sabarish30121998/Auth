package com.example.OtpGeneration.Repository;

import com.example.OtpGeneration.Entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepo extends JpaRepository<UsersRepo,Short> {
    List<UserRole> findByUsersEmail(String email);
}
