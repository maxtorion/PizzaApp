package jpa;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.joining;

@Entity
@Table(schema = "pizza_database", name = "user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable {

    //na potrzeby jpa tylko i wyłącznie
    protected User() {
    }

    public User(String login, String password, String email, String telefon, AccountStatus status, AccountType type) {
        this(login, password, email, telefon);
        Status = status;
        Type = type;
    }

    public User(String login, String password, String email, String telefon, AccountStatus status) {
        this(login, password, email, telefon);
        Status = status;
    }

    public User(String login, String password, String email, String telefon, AccountType type) {
        this(login, password, email, telefon);
        Type = type;
    }

    public User(String login, String password, String email, String telefon) {
        Login = login;
        Password = password;
        Email = email;
        Telefon = telefon;
    }

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdUser;

    public Integer getId() {
        return IdUser;
    }

    public void setId(Integer Id) {
        this.IdUser = Id;
    }

    @Column(name = "Login", nullable = false, unique = true, length = 50)
    @NotNull
    @Size(min = 4, max = 20)
    private String Login;

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    @NotNull
    @Size(min = 4, max = 20)
    @Column(name = "Password", nullable = false, length = 50)
    private String Password;

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Email
    @NotBlank
    @Column(name = "Email", nullable = false, unique = true, length = 50)
    private String Email;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @NotNull
    @Size(min = 9, max = 20)
    @Column(name = "Telefon", nullable = false, unique = true, length = 50)
    private String Telefon;

    public String getTelefon() {
        return Telefon;
    }

    public void setTelefon(String telefon) {
        Telefon = telefon;
    }

    @Column(name = "Status")
    @Enumerated(EnumType.STRING)
    private AccountStatus Status = AccountStatus.Disabled;

    public AccountStatus getStatus() {
        return Status;
    }

    public void setStatus(AccountStatus status) {
        Status = status;
    }

    @Column(name = "Type")
    @Enumerated(EnumType.STRING)
    private AccountType Type = AccountType.User;

    public AccountType getType() {
        return Type;
    }

    public void setType(AccountType type) {
        Type = type;
    }


    @OneToMany(targetEntity = Order.class, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.setUser(this);
    }

    public void removeOrder(Order order) {
        order.setUser(null);
        this.orders.remove(order);
    }

    @Override
    public String toString() {
        // final String allOrders = this.orders.stream().map(order -> order.toString()).collect(joining("/"));
        return String.format("User[userID=%d,Login=%s,Password=%s,Email=%s,Telefon=%s,Status=%s,Type=%s]"
                , IdUser, Login, Password, Email, Telefon, Status, Type);
    }
}
