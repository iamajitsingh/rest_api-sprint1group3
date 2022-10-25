package com.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.DeliveryPerson;
import com.model.Request;

@Repository
public interface DeliveryPersonDao extends JpaRepository<DeliveryPerson, Integer> {

    Optional<DeliveryPerson> findByRequest(Request request);

}
