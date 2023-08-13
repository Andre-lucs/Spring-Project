package com.andrelucs.demoProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andrelucs.demoProject.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{


}