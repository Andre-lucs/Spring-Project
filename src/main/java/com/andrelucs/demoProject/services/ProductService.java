package com.andrelucs.demoProject.services;

import com.andrelucs.demoProject.entities.Product;
import com.andrelucs.demoProject.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository repository;

    public List<Product> findAll(){
        return repository.findAll();
    }

    public Product findById(Long id){
        Optional<Product> u = repository.findById(id);
        return u.get();
    }

}
