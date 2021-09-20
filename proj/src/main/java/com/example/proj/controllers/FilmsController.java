package com.example.proj.controllers;

import com.example.proj.models.Film;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FilmsController {
    private static final List<Film> films = new ArrayList<>();

    static {
        films.add(new Film("Lord of rings", "Pieter Jackson", "Fantasy", 2001));
        films.add(new Film("The Shawshank Redemption", "Nicky Marvin", "Drama", 1994));
        films.add(new Film("Forest Gump", "Robert Zemeckis", "Comedy, Drama", 1994));
    }

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public ModelAndView index(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        //model.addAttribute("message", message);

        return modelAndView;
    }

    @RequestMapping(value = {"/listfilms"}, method = RequestMethod.GET)
    public ModelAndView personList(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("films");
        model.addAttribute("films", films);
        return modelAndView;
    }
}
