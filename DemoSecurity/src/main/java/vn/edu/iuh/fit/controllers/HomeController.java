package vn.edu.iuh.fit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(){
        return "/index";
    }

    @GetMapping("/logon")
    public String login(Principal principal) {
        if (principal != null) {
            return "redirect:/home";
        }
        return "/logon";
    }
}
