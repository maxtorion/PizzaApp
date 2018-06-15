package jpa.pizza;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;




public interface  PizzaRepository extends JpaRepository<Pizza,String> {

    Pizza findPizzaByPizzaname(@Param("pizzaName") String pizzaName);

}
