package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Device;
@Repository
public interface DeviceDao extends JpaRepository<Device,Integer>{

}