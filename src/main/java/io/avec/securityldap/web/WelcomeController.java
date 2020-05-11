package io.avec.securityldap.web;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "index";
    }

//    @GetMapping("/hello")
//    public String hello(Model model, Principal principal) {
//        model.addAttribute("user", principal.getName());
//        return "hello";
//    }

    @GetMapping("/hello")
    public String hello(Model model, Authentication authentication) {
        model.addAttribute("user", authentication.getName());
        return "hello";
    }
}
