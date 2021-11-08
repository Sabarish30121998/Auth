package com.example.OtpGeneration.Repository;

import com.example.OtpGeneration.Entity.OAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthRepo extends JpaRepository<OAuth, Short> {
}
