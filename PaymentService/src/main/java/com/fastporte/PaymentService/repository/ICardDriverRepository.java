package com.fastporte.PaymentService.repository;

import com.fastporte.PaymentService.entities.CardDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICardDriverRepository extends JpaRepository<CardDriver, Long> {

}
