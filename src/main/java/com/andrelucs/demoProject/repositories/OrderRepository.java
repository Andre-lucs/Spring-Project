package com.andrelucs.demoProject.repositories;

import com.andrelucs.demoProject.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
