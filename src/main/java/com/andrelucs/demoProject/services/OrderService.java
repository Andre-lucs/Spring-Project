package com.andrelucs.demoProject.services;

import com.andrelucs.demoProject.entities.Order;
import com.andrelucs.demoProject.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository repository;

    public List<Order> findAll(){
        return repository.findAll();
    }

    public Order findById(Long id){
        Optional<Order> u = repository.findById(id);
        return u.get();
    }

}
