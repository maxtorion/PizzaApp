package jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    //TODO: nie jestem tego jeszcze pewny, do sprawdzenia
    //Optional<User> findUserByLoginAndAndPassword(@Param("Login") String Login, @Param("Password") String Password);
}
