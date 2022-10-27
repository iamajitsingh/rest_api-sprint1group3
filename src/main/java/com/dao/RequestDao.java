package com.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.model.Request;
@Repository
public interface RequestDao extends JpaRepository<Request,Integer>{

	List<Request> findByDate(Date date);

}
