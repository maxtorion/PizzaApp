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
        if(user.getLogin() != "" && user.getPassword() != "") {
            //TODO sprawdzenie czy user jest w bazie


            //TODO sprawdzenie czy to admin czy user (JEZELI USER - czy jest ENABLE)

            return "user";
        }
        else return "start";
    }



    //tymczasowe wejscie na panel admina
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    //test
    @PostMapping(path="/start" , params="registerParam")
    public String register(@ModelAttribute User user){
        return "start";
    }


}
