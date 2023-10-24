package oit.is.lec05.team6.kounyu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class Sample51Controller {
    @GetMapping("/sample5")
    public String Saple51(){
        return "sample51.html";
    }
}
