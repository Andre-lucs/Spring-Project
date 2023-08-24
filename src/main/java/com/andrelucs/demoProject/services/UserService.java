package com.andrelucs.demoProject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.andrelucs.demoProject.services.exceptions.DatabaseException;
import com.andrelucs.demoProject.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.andrelucs.demoProject.entities.User;
import com.andrelucs.demoProject.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(Long id){
        Optional<User> u = repository.findById(id);
        return u.orElseThrow(()-> new ResourceNotFoundException(id));
    }

    public List<String> findAllPhones() {
        List<User> userList = findAll();
        List<String> phones = new ArrayList<>();
        for(User u : userList){
            phones.add(u.getPhone());
        }
        return phones;
    }

    public User insert(User obj){
        return repository.save(obj);
    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }catch(DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public User update(Long id, User obj){
        try {
            User u = repository.getReferenceById(id);
            updateData(u, obj);
            return repository.save(u);
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User entity, User obj){
        if(obj.getName() != null)
            entity.setName(obj.getName());
        if(obj.getEmail() != null)
            entity.setEmail(obj.getEmail());
        if(obj.getPhone() != null)
            entity.setPhone(obj.getPhone());
    }
}
