package com.example.OtpGeneration.Repository;

import com.example.OtpGeneration.Entity.OAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OAuthRepo extends JpaRepository<OAuth, Short> {
    Optional<OAuth> findByUserIdFk(short id);
}
