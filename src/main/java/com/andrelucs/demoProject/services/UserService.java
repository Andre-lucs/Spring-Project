package com.andrelucs.demoProject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return u.get();
    }

    public List<String> findAllPhones() {
        List<User> userList = findAll();
        List<String> phones = new ArrayList<>();
        for(User u : userList){
            phones.add(u.getPhone());
        }
        return phones;
    }

}