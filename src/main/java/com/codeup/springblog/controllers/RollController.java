package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RollController {

    @GetMapping("/roll-dice")
    public String rollView(Model model) {
        model.addAttribute("title", "Let's roll some dice");
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

        model.addAttribute("title", "Rolled results");
        model.addAttribute("message", message);
        model.addAttribute("number", number);
        model.addAttribute("random", random);
        return "dice/rolled-dice";
    }

}
