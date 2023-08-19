package com.andrelucs.demoProject.repositories;

import com.andrelucs.demoProject.entities.OrderItem;
import com.andrelucs.demoProject.entities.pk.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{


}