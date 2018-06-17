package jpa;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "pizza_database", name = "pizza")
public class Pizza implements Serializable {

    @Id
    @Column(name = "pizza_name",length = 150)
    private String pizzaname;

    @Column(name = "Price",
    nullable = false)
    private float price;

    @Column(name = "Ingredients",
    nullable = false,length = 155)
    private String ingredients;


 //  @JsonBackReference
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.pizza",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Set<OrderedPizza> orderedPizzas = new HashSet<>();

    public Set<OrderedPizza> getOrderedPizzas() {
        return orderedPizzas;
    }

    public void setOrderedPizzas(Set<OrderedPizza> orderedPizzas) {
        this.orderedPizzas = orderedPizzas;
    }


    //na potrzeby jpa tylko i wyłącznie
    protected Pizza(){}

    public void setPizzaname(String pizzaname) {
        this.pizzaname = pizzaname;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }


    public String getPizzaname() {
        return pizzaname;
    }

    public float getPrice() {
        return price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public Pizza(String Pizza_name, float price, String ingredients) {

        this.pizzaname = Pizza_name;
        this.price = price;
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return String.format("Pizza[Pizza_name=%s, Price=%f, Ingredients=%s]",pizzaname,price,ingredients);
    }
}
