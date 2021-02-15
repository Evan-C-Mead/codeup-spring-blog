package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RollController {

    @GetMapping("/roll-dice")
    public String rollView() {
        return "dice/roll";
    }

    @GetMapping("/roll-dice/{number}")
    public String rolledDiceView(@PathVariable int number, Model model) {
        int random = (int) Math.ceil(Math.random() * 6);
        String message;

        if (random == number) {
            message = "You guessed the roll correctly!";
        } else {
            message = "Sorry, try again.";
        }

        model.addAttribute("message", message);
        model.addAttribute("number", number);
        model.addAttribute("random", random);
        return "dice/rolled-dice";
    }

}
