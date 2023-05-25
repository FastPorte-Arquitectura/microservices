package com.fastporte.PaymentService.repository;

import com.fastporte.PaymentService.entities.CardClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICardClientRepository extends JpaRepository<CardClient, Long> {

}