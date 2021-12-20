package com.example.lab1.controller;

import com.example.lab1.forms.TaskForm;
import com.example.lab1.models.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping
public class ControllerTask {
    private static final List<Task> tasks = new ArrayList<>();

    static {
        tasks.add(new Task(1,"Проснуться", "В 7 утра, почти невозможна"));
        tasks.add(new Task(2,"Успеть на пару к 8", "Бегом до остановки"));
        tasks.add(new Task(3,"Поспать", "Сесть на последние ряды"));
    }

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @Value("${error.message2}")
    private String errorMessage2;

    @GetMapping(value = {"/", "index"})
    public ModelAndView index(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        model.addAttribute("message", message);

        return modelAndView;
    }

    @GetMapping(value = "/gettasks")
    public ModelAndView personList(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tasks");
        model.addAttribute("tasks", tasks);
        log.info("Get all task;");
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView showAddPersonPage(Model model, String error) {
        ModelAndView modelAndView = new ModelAndView("add");
        TaskForm taskForm = new TaskForm();
        model.addAttribute("taskForm", taskForm);
        log.info("Get new task page;");
        if(error != null)
            model.addAttribute("errorMessage", error);
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView addTask(Model model, @ModelAttribute("TaskForm") TaskForm taskForm) {
        ModelAndView modelAndView = new ModelAndView();

        String title = taskForm.getName();
        String action = taskForm.getTask();

        if (title != null && title.length() > 0 && action != null && action.length() > 0) {
            Task newTask = new Task(tasks.get(tasks.size()-1).getId() + 1, title, action);
            tasks.add(newTask);
            modelAndView.setViewName("tasks");
            model.addAttribute("tasks",tasks);
            log.info("Add new task;");
            return modelAndView;
        }

        modelAndView = showAddPersonPage(model, errorMessage);
        return modelAndView;
    }

    @GetMapping(value = "/rplc")
    public ModelAndView runReplaceAddPersonPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("rplc");
        TaskForm taskForm = new TaskForm();
        model.addAttribute("taskForm", taskForm);
        return modelAndView;
    }

    @PostMapping(value = "/rplc")
    public ModelAndView replaceTask(Model model, @ModelAttribute("TaskForm") TaskForm taskForm) {
        ModelAndView modelAndView = new ModelAndView();
        Integer id = -1;

        modelAndView.setViewName("tasks");
        if(taskForm.getId() != null)
            id = taskForm.getId();

        String title = taskForm.getName();
        String action = taskForm.getTask();

        if (id > 0 && title.length() > 0
                && action != null && action.length() > 0) {
            Task newTask = new Task(id, title, action);
            int index = -1;
            for (int i = 0; i < tasks.size(); i++) {
                if(tasks.get(i).getId().equals(id)){
                    index = i;
                    break;
                }
            }
            if(index != -1){
                tasks.set(index, newTask);
            }
            model.addAttribute("tasks",tasks);
            return modelAndView;
        }

        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("rplc");
        return modelAndView;
    }

    @GetMapping(value = "/del")
    public ModelAndView delTask(Model model, @ModelAttribute("TaskForm") TaskForm taskForm) {
        ModelAndView modelAndView = new ModelAndView();
        Integer id = -1;

        modelAndView.setViewName("del");
        if(taskForm.getId() != null)
            id = taskForm.getId();
        else {
            modelAndView.setViewName("del");
            return modelAndView;
        }

        int index = -1;
        if (id > 0) {
            for (int i = 0; i < tasks.size(); i++) {
                if(tasks.get(i).getId().equals(id)){
                    index = i;
                    break;
                }
            }
            if(index >= 0){
                tasks.remove(index);
                log.info(String.format("Deleted task id: %s", index));
            }else {
                model.addAttribute("errorMessage", errorMessage2);
                log.error(String.format("Not founded id: %s", id));
                return modelAndView;
            }
        }else
        {
            model.addAttribute("errorMessage", errorMessage);
        }
        modelAndView.setViewName("del");
        return modelAndView;
    }

}
