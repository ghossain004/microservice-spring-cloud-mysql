package com.simec.securityservice.repository;

import com.simec.securityservice.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialRepository  extends JpaRepository<UserCredential, Long> {

    Optional<UserCredential> findByName(String username);
}
