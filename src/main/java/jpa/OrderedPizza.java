package jpa;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "pizza_database", name = "ordered_pizza")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AssociationOverrides({
        @AssociationOverride(name="pk.pizza", joinColumns = @JoinColumn(name = "pizza_name")),
        @AssociationOverride(name="pk.order", joinColumns = @JoinColumn(name = "ord_id"))
})
public class OrderedPizza implements Serializable {

    protected OrderedPizza() {
    }

    public OrderedPizza(OrderedPizzaKey pk, Integer quantity) {
        this.pk = pk;
        Quantity = quantity;
    }

    @EmbeddedId
    private OrderedPizzaKey pk = new OrderedPizzaKey();

    public OrderedPizzaKey getPk() {
        return pk;
    }

    public void setPk(OrderedPizzaKey pk) {
        this.pk = pk;
    }



    @Column(name = "Quantity")
    private Integer Quantity = 1;

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }
}
