package com.example.WebRestaurant;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "html/MainPage";
    }

    @GetMapping("/greeting")
    public String greeting() {
        return "html/Greeting";
    }

    @GetMapping("/philosophy")
    public String philosophy() {
        return "html/Philosophy";
    }

    @GetMapping("/koreamenu")
    public String koreamenu() {
        return "html/KoreaMenu";
    }

    @GetMapping("/chinamenu")
    public String chinamenu() {
        return "html/ChinaMenu";
    }

    @GetMapping("/westernmenu")
    public String westernmenu() {
        return "html/WesternMenu";
    }

    @GetMapping("/branch")
    public String branch() {
        return "html/Branch";
    }

    @GetMapping("/simulation")
    public String simulation() {
        return "html/Simulation";
    }
}
