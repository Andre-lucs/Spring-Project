package com.andrelucs.demoProject.resources;

import com.andrelucs.demoProject.entities.Order;
import com.andrelucs.demoProject.entities.Payment;
import com.andrelucs.demoProject.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/payments")
public class PaymentResource {
	
	@Autowired
	private PaymentService service;

	@GetMapping
	public ResponseEntity<List<Payment>> findAll(){
		List<Payment> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Payment> findById( @PathVariable Long id){
		Payment u = service.findById(id);
		return ResponseEntity.ok().body(u);
	}

	@GetMapping(value = "/{id}/order")
	public ResponseEntity<Order> findOrder(@PathVariable Long id){
		Payment u = service.findById(id);
		return ResponseEntity.ok().body(u.getOrder());
	}
}
