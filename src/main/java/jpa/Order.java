package jpa;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "pizza_database", name = "pizza_ord")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order implements Serializable {

    //dla jpa tylko i wyłącznie
    protected Order() {
    }

    public Order(User user, Double sum, Date date, String street, Integer Apartment_number, String region, String city) {

        this(user, date, street, Apartment_number, region, city);
        Sum = sum;

    }

    public Order(User user, Date date, String street, Integer Apartment_number, String region, String city) {
        this.setUser(user);
        this.date = date;
        Street = street;
        this.Apartment_number = Apartment_number;
        Region = region;
        City = city;
    }

    public Order(Integer user_id, Double sum, Date date, String street, Integer apartment_number, String region, String city) {
        this.user_id = user_id;
        Sum = sum;
        this.date = date;
        Street = street;
        Apartment_number = apartment_number;
        Region = region;
        City = city;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderyID;

    public Integer getOrderyID() {
        return orderyID;
    }

    public void setOrderyID(Integer orderyID) {
        this.orderyID = orderyID;
    }


    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",updatable = false,insertable = false)
    @JsonIgnore
    private User user;

    public User GetUser_id() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.user_id = user.getId();
    }

    //zwrócić na to uwagę, trochę prowizorka
    @Column(name = "user_id")
    private Integer user_id;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Column(name = "Sum")
    private Double Sum = 0.0;

    public Double getSum() {
        return Sum;
    }

    public void setSum(Double sum) {
        Sum = sum;
    }

    @Column(name = "Date", nullable = false)
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "Street", nullable = false, length = 200)
    private String Street;

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    @Column(name = "Apartment_number", nullable = false)
    private Integer Apartment_number;

    public Integer getApartment_number() {
        return Apartment_number;
    }

    public void setApartment_number(Integer Apartment_number) {
        this.Apartment_number = Apartment_number;
    }

    @Column(name = "Region", nullable = false, length = 200)
    private String Region;

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    @Column(name = "City", nullable = false, length = 200)
    private String City;

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

 //   @JsonBackReference
    @OneToMany(mappedBy = "pk.order",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Set<OrderedPizza> orderedPizzas = new HashSet<>();

    public Set<OrderedPizza> getOrderedPizzas() {
        return orderedPizzas;
    }

    public void setOrderedPizzas(Set<OrderedPizza> orderedPizzas) {
        this.orderedPizzas = orderedPizzas;
    }


    @Override
    public String toString() {
        return String.format("Order[orderyID=%d,user_Id=%d,Sum=%f,Date=%s,Street=%s,Apartment_number=%d,Region=%s,City=%s]"
                , this.orderyID, this.user.getId(), this.Sum, this.date.toString(), this.Street, this.Apartment_number, this.Region, this.City);
    }
}
