package controllers;

import jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderedpizzaapi")
@EnableJpaRepositories(basePackages = "jpa")
public class OrderedPizzaRestController {

    private OrderedPizzaRepository orderedPizzaRepository;
    private PizzaRepository pizzaRepository;
    private OrderRepository orderRepository;

    @Autowired
    public OrderedPizzaRestController(OrderedPizzaRepository orderedPizzaRepository, PizzaRepository pizzaRepository, OrderRepository orderRepository) {
        this.orderedPizzaRepository = orderedPizzaRepository;
        this.pizzaRepository = pizzaRepository;
        this.orderRepository = orderRepository;
    }

    //bardziej pod testy niż jakieś praktyczne zastosowanie
    @GetMapping("/all")
    public List<OrderedPizza> getAllOrderedPizzas()
    {
        return this.orderedPizzaRepository.findAll();
    }




    //TODO:Rozwiązać kwestię dodawania zamówień
    @PostMapping(value ="/orderedpizza")
    ResponseEntity<?> addOrderedPizza(@RequestBody OrderedPizza orderedPizza)
    {
            Pizza pizza = pizzaRepository.findPizzaByPizzaname(orderedPizza.getPk().getPizza().getPizzaname());
            Order order = orderRepository.getOne(orderedPizza.getPk().getOrder().getOrderyID());
            OrderedPizza newOrderedPizza = new OrderedPizza(new OrderedPizzaKey(order,pizza),orderedPizza.getQuantity());
            this.orderedPizzaRepository.save(newOrderedPizza);
            return ResponseEntity.status(HttpStatus.CREATED).build();

    }


}
