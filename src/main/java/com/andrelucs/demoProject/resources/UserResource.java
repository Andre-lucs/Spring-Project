package com.andrelucs.demoProject.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrelucs.demoProject.entities.User;
import com.andrelucs.demoProject.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById( @PathVariable Long id){
		User u = service.findById(id);
		return ResponseEntity.ok().body(u);
	}

	@GetMapping(value = "/phones")
	public ResponseEntity<List<String>> findAllPhones(){
		List<String> phones = service.findAllPhones();
		return ResponseEntity.ok().body(phones);
	}
}
