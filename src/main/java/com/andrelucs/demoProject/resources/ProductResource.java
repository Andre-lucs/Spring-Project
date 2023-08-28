package com.andrelucs.demoProject.resources;

import com.andrelucs.demoProject.entities.Product;
import com.andrelucs.demoProject.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value="/products")
public class ProductResource {
	
	@Autowired
	private ProductService service;

	@GetMapping
	public ResponseEntity<List<Product>> findAll(){
		List<Product> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById( @PathVariable Long id){
		Product u = service.findById(id);
		return ResponseEntity.ok().body(u);
	}

	@PostMapping
	public ResponseEntity<Product> insert(@RequestBody Product obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
