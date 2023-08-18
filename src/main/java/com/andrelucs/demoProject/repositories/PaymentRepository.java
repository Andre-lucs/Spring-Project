package com.andrelucs.demoProject.repositories;

import com.andrelucs.demoProject.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
