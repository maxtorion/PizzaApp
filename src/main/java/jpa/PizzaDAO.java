package jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaDAO {


    private PizzaRepository pizzaRepository;

    @Autowired
    public PizzaDAO(PizzaRepository pizzaRepository) {

        this.pizzaRepository =pizzaRepository;
    }

    public Pizza addPizza(Pizza pizza)
    {
        return  pizzaRepository.save(pizza);
    }

    public List<Pizza> getAllPizzas()
    {
        return pizzaRepository.findAll();
    }

    public Pizza getPizza(String pizzaName)
    {
        return pizzaRepository.findPizzaByPizzaname(pizzaName);
    }

    public void deletePizza(Pizza pizza)
    {
        pizzaRepository.delete(pizza);
    }

}
