package jpa;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderedPizzaKey implements Serializable {

    protected OrderedPizzaKey() {
    }

    public OrderedPizzaKey(Order order, Pizza pizza) {
        this.order = order;
        this.pizza = pizza;
    }

    @JsonIgnore
    //@JsonManagedReference
    @ManyToOne
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


    @JsonManagedReference
    @ManyToOne
    private Pizza pizza;

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderedPizzaKey)) return false;
        OrderedPizzaKey that = (OrderedPizzaKey) o;
        return Objects.equals(getOrder(), that.getOrder()) &&
                Objects.equals(getPizza(), that.getPizza());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getOrder(), getPizza());
    }
}
