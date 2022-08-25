package com.AdopcionMascotas.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String index() {
        return "/login";
    }

    @GetMapping("/logout")
    public String login() {
        return "/login";
    }


}
