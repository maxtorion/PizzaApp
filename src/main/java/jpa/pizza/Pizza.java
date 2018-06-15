package jpa.pizza;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(schema = "pizzaeriadatabse", name = "pizza")
public class Pizza implements Serializable {

    @Id
    @Column(name = "pizza_name")
    private String pizzaname;

    @Column(name = "Price")
    private float price;

    @Column(name = "Ingredients")
    private String ingredients;

    protected Pizza(){}

    public Pizza(String Pizza_name, float price, String ingredients) {
        this.pizzaname = Pizza_name;
        this.price = price;
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

    @Override
    public String toString() {
        return String.format("Pizza[Pizza_name=%s, Price=%f, Ingredients=%s]",pizzaname,price,ingredients);
    }
}
