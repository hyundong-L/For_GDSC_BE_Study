package com.example.SpringBoot3.First_controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hi")
    public String niceToMeetyou(Model model) {
        model.addAttribute("username","Lee");
        return "greetings";
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("nickname", "Lee");
        return "goodbye";
    }

    @GetMapping("random-quote")
    public String randomQuote(Model model) {
        String[] word = {"안녕", "잘가", "반가워", "조심히 가", "재밌다"};

        int randInt = (int) (Math.random() * word.length);
        model.addAttribute("randWord", word[randInt]);

        return "quote";
    }
}
