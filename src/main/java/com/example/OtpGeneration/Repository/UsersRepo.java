package com.example.OtpGeneration.Repository;

import com.example.OtpGeneration.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<Users, Short> {
    Optional<Users> findByEmail(String email);
}
