package com.andrelucs.demoProject.services;

import com.andrelucs.demoProject.entities.*;
import com.andrelucs.demoProject.entities.enums.OrderStatus;
import com.andrelucs.demoProject.repositories.OrderItemRepository;
import com.andrelucs.demoProject.repositories.OrderRepository;
import com.andrelucs.demoProject.repositories.ProductRepository;
import com.andrelucs.demoProject.repositories.UserRepository;
import com.andrelucs.demoProject.services.exceptions.ConflictException;
import com.andrelucs.demoProject.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findById(Long id){
        Optional<Order> u = orderRepository.findById(id);
        return u.orElseThrow(()->new ResourceNotFoundException(id));
    }

    public Order insert(Order obj){
        if(obj.getPayment() == null) obj.setOrderStatus(OrderStatus.WAITING_PAYMENT);
        User u = userRepository.findById(obj.getClient().getId())
                .orElseThrow(()->new ResourceNotFoundException(obj.getClient().getId(), "User"));
        obj.setClient(u);
        Order order = orderRepository.save(obj);
        addItems(order.getId(), obj.getItems());
        return order;
    }

    public Order addItems(Long orderId, Set<OrderItem> items){
        Order order = orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFoundException(orderId));
        for(OrderItem oi : items){
            Product p = productRepository.findById(oi.getProduct().getId())
                    .orElseThrow(()->new ResourceNotFoundException(oi.getProduct().getId(), "Product"));

            if(oi.getValue() == null){
                oi.setValue(p.getPrice());
            }
            oi.setProduct(p);
            oi.setOrder(order);

            order.getItems().add(oi);
        }
        orderItemRepository.saveAll(order.getItems());
        return order;
    }

    public Order insertPayment(Long orderId, Payment payment) {
        try {
            Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException(orderId));
            if(order.getPayment() != null) throw new ConflictException(orderId);
            order.setPayment(payment);
            payment.setOrder(order);
            if(order.getOrderStatus() == OrderStatus.WAITING_PAYMENT)
                order.setOrderStatus(OrderStatus.PAID);
            return orderRepository.save(order);
        }catch(DataIntegrityViolationException e){
            throw new ConflictException(orderId);
        }
    }

    public Order updateStatus(Long orderId, String newStatus){
        OrderStatus s;
        try {
            Integer code = Integer.parseInt(newStatus);
            s = OrderStatus.valueByCode(code).orElse(null);
        } catch (NumberFormatException e){
            s = OrderStatus.valueByName(newStatus).orElse(null);
        }
        if(s == null) throw new ResourceNotFoundException(orderId, "OrderStatus");
        Order o = orderRepository.getReferenceById(orderId);
        o.setOrderStatus(s);
        return orderRepository.save(o);
    }

	public Order increaseStatusLevel(Long id) {
		Order o = findById(id);
		o.setOrderStatus(OrderStatus.valueByCode(o.getOrderStatus().getCode()+1).orElse(OrderStatus.DELIVERED));
		return orderRepository.save(o);
	}
}
