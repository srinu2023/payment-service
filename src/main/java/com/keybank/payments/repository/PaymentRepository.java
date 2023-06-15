package com.keybank.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keybank.payments.entity.Payments;


public interface PaymentRepository extends JpaRepository<Payments, Integer> {

}
