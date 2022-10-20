package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Repair;

@Repository
public interface RepairDao extends JpaRepository<Repair, Integer> {

}
