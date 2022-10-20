package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Complaints;

@Repository
public interface ComplaintsDao extends JpaRepository<Complaints, Integer> {

}
