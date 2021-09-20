package com.example.lab1.controller;

import com.example.lab1.forms.TaskForm;
import com.example.lab1.models.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@org.springframework.stereotype.Controller
public class ControllerTask {
    private static final List<Task> tasks = new ArrayList<>();

    static {
        tasks.add(new Task("Проснуться", "В 7 утра, почти невозможна"));
        tasks.add(new Task("Успеть на пару к 8", "Бегом до остановки"));
        tasks.add(new Task("Поспать", "Сесть на последние ряды"));
    }

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public ModelAndView index(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        model.addAttribute("message", message);

        return modelAndView;
    }

    @RequestMapping(value = {"/gettasks"}, method = RequestMethod.GET)
    public ModelAndView personList(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tasks");
        model.addAttribute("tasks", tasks);
        return modelAndView;
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public ModelAndView showAddPersonPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("add");
        TaskForm taskForm = new TaskForm();
        model.addAttribute("taskForm", taskForm);
        return modelAndView;
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public ModelAndView addTask(Model model, @ModelAttribute("TaskForm") TaskForm taskForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tasks");
        String title = taskForm.getName();
        String action = taskForm.getTask();
        if (title != null && title.length() > 0
                && action != null && action.length() > 0) {
            Task newTask = new Task(title, action);
            tasks.add(newTask);
            model.addAttribute("tasks",tasks);
            return modelAndView;
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("add");
        return modelAndView;
    }

    @RequestMapping(value = {"/del"}, method = RequestMethod.GET)
    public ModelAndView delTask(Model model, @ModelAttribute("TaskForm") TaskForm taskForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tasks");
        String title = taskForm.getName();
        int index = -1;
        if (title != null && title.length() > 0) {
            for (int i = 0; i < tasks.size(); i++) {
                if(tasks.get(i).getName().equals(title)){
                    index = i;
                    break;
                }
            }
            if(index >= 0){
                tasks.remove(index);
            }
            model.addAttribute("tasks",tasks);
            return modelAndView;
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("del");
        return modelAndView;
    }
}
