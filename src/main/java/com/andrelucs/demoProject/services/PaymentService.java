package com.andrelucs.demoProject.services;

import com.andrelucs.demoProject.entities.Payment;
import com.andrelucs.demoProject.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    
    @Autowired
    private PaymentRepository repository;

    public List<Payment> findAll(){
        return repository.findAll();
    }

    public Payment findById(Long id){
        Optional<Payment> u = repository.findById(id);
        return u.get();
    }

}
