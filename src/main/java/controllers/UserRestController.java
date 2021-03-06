package controllers;


import jpa.User;
import jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/userapi")
@EnableJpaRepositories(basePackageClasses = jpa.UserRepository.class)
public class UserRestController {


    private UserRepository userRepository;

    @Autowired
    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/allusers")
    public List<User> getAllUsers()
    {
        return this.userRepository.findAll();
    }

    @GetMapping("/findbyid/{userID}")
    public User getUserByID(@PathVariable Integer userID)
    {
        return this.userRepository.getOne(userID);
    }

    @GetMapping("/findbylogin/{login}")
    public Optional<User> getUserByLogin(@PathVariable String login)
    {
        return this.userRepository.findUserByLogin(login);
    }

    @PostMapping("/user")
    ResponseEntity<?> addUser(@RequestBody User user)
    {
        if( user.getId()!= null && this.userRepository.existsById(user.getId()))
        {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
        }
        else
        {
            this.userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }
}
