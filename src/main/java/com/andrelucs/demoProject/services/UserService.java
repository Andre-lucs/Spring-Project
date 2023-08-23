package com.andrelucs.demoProject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.andrelucs.demoProject.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
        repository.deleteById(id);
    }

    public User update(Long id, User obj){
        User u = repository.getReferenceById(id);
        updateData(u, obj);
        return repository.save(u);
    }

    private void updateData(User entity, User obj){
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }
}
