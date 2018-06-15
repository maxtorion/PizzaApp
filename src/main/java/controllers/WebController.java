package controllers;


import jpa.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    @GetMapping("/start")
    public String start(Model model){

        model.addAttribute("user", new User("","","",""));

        return "start";
    }

    @PostMapping(path="/start" , params="loginParam")
    public String goToUser(@ModelAttribute User user){
        if(user.getLogin() != null && user.getPassword() != null) {
            //TODO sprawdzenie czy user jest w bazie


            return "user";
        }
        else return "start";
    }



}