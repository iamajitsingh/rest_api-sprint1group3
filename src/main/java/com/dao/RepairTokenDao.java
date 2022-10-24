package com.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.RepairToken;

public interface RepairTokenDao extends JpaRepository<RepairToken, Integer> {

  	Optional<RepairToken> findByToken(String token);

}
