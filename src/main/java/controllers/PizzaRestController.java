package controllers;

import jpa.Pizza;
import jpa.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pizza")
@EnableJpaRepositories(basePackages = "jpa")
public class PizzaRestController {


    private PizzaRepository pizzaRepository;

    @Autowired
    public PizzaRestController(PizzaRepository pizzaRepository) {

        this.pizzaRepository = pizzaRepository;
    }


    @GetMapping("/{pizzaName}")
    public Pizza getPizza(@PathVariable String pizzaName) {
        return this.pizzaRepository.findPizzaByPizzaname(pizzaName);
    }




}
