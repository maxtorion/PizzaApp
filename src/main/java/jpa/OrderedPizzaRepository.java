package jpa;


import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedPizzaRepository extends JpaRepository<OrderedPizza,OrderedPizzaKey> {

}
