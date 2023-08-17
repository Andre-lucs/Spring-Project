package com.andrelucs.demoProject.services;

import com.andrelucs.demoProject.entities.Category;
import com.andrelucs.demoProject.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll(){
        return repository.findAll();
    }

    public Category findById(Long id){
        Optional<Category> u = repository.findById(id);
        return u.get();
    }

}
