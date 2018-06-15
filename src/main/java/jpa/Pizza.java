package jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(schema = "pizza_database", name = "pizza")
public class Pizza implements Serializable {

    @Id
    @Column(name = "pizza_name")
    private String pizzaname;

    @Column(name = "Price",
    nullable = false)
    private float price;

    @Column(name = "Ingredients",
    nullable = false)
    private String ingredients;

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
