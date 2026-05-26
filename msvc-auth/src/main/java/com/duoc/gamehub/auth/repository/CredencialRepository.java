package com.duoc.gamehub.auth.repository;

import com.duoc.gamehub.auth.model.entity.Credencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredencialRepository extends JpaRepository<Credencial, Long> {
    Optional<Credencial> findByEmail(String email);
    boolean existsByEmail(String email);
}