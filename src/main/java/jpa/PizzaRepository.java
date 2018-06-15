package jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface  PizzaRepository extends JpaRepository<Pizza,String> {

    Pizza findPizzaByPizzaname(@Param("pizzaName") String pizzaName);

}
