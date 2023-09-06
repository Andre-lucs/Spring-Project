package com.andrelucs.demoProject.resources;

import com.andrelucs.demoProject.entities.Order;
import com.andrelucs.demoProject.entities.OrderItem;
import com.andrelucs.demoProject.entities.Payment;
import com.andrelucs.demoProject.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value="/orders")
public class OrderResource {
	
	@Autowired
	private OrderService service;

	@GetMapping
	public ResponseEntity<List<Order>> findAll(){
		List<Order> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/")
	public ResponseEntity<Order> findById( @RequestParam("id") Long id){
		Order u = service.findById(id);
		return ResponseEntity.ok().body(u);
	}

	@PostMapping
	public ResponseEntity<Order> insert(@RequestBody Order obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(obj.getId())
            .toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@PutMapping(value = "/items/")
	public ResponseEntity<Order> addItems(@RequestParam("id") Long id, @RequestBody Set<OrderItem> orderItems){
		Order response = service.addItems(id, orderItems);
		return ResponseEntity.ok().body(response);
	}
    @PutMapping(value = "/payment/")
    public ResponseEntity<Order> insertPayment(@RequestParam("id") Long id,@RequestBody Payment payment){
        Order response = service.insertPayment(id, payment);
        return ResponseEntity.ok().body(response);
    }

	@PutMapping(value = "/status/")
	public ResponseEntity<Order> updateStatus(@RequestParam("id")Long id, @RequestParam("status") String newStatus){
        Order response = service.updateStatus(id, newStatus);
		return ResponseEntity.ok().body(response);
	}
	
	@PutMapping(value = "/increase-status-level/")
	public ResponseEntity<Order> increaseStatusLevel(@RequestParam("id") Long id){
		Order response = service.increaseStatusLevel(id);
		return ResponseEntity.ok().body(response);
	}
}
