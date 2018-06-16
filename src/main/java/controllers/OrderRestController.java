package controllers;


import jpa.Order;
import jpa.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderapi")
@EnableJpaRepositories(basePackages = "jpa")
public class OrderRestController {

    private OrderRepository orderRepository;

    @Autowired
    public OrderRestController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/allorders")
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    @GetMapping("/{orderID}")
    public Order getOrderByID(@PathVariable Integer orderID)
    {
        return this.orderRepository.getOne(orderID);
    }

    @PostMapping(value = "/order")
    ResponseEntity<?> addOrder(@RequestBody Order order)
    {
        if( order.getOrderyID()!= null && this.orderRepository.existsById(order.getOrderyID()))
        {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
        }
        else
        {
            this.orderRepository.save(order);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

}
