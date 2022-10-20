package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.model.Tracking;
@Repository
public interface TrackingDao extends JpaRepository<Tracking,Integer>{


}