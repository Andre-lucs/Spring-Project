package com.andrelucs.demoProject.services;

import com.andrelucs.demoProject.entities.Category;
import com.andrelucs.demoProject.entities.Product;
import com.andrelucs.demoProject.repositories.ProductRepository;
import com.andrelucs.demoProject.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

	public Product insert(Product obj){
		return repository.save(obj);
	}

	public Product update(Long id, Product obj){
        try {
            Product u = repository.getReferenceById(id);
            updateData(u, obj);
            return repository.save(u);
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Product entity, Product obj){
        entity.setName(obj.getName());
        entity.setDescription(obj.getDescription());
        entity.setPrice(obj.getPrice());
		entity.setImgUrl(obj.getImgUrl());
		
		entity.getCategories().clear();
		Set<Category> newCategories = entity.getCategories();
		for(Category c : obj.getCategories()){
			newCategories.add(c);
		}
    }

}
