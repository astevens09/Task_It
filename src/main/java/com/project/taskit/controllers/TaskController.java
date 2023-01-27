package com.project.taskit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
public class TaskController {

    @GetMapping("/tasks")
    public String getTaskPage(){
        //Get task from database

        return "tasksPage";
    }

    @PostMapping("/tasks/create")
    public String createTask(Model model,
                             @RequestParam String action,
                             @RequestParam String completed,
                             @RequestParam String category,
                             @RequestParam String date) throws ParseException {

        System.out.println("Date: "+date);
        System.out.println("action: "+action);
        System.out.println("completed: "+completed);
        System.out.println("category: "+category);



//        SimpleDateFormat oldFormatter = new SimpleDateFormat("yyyy-dd-mm", Locale.ENGLISH);
        SimpleDateFormat newFormatter =new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

        System.out.println("new date: "+dateFormat(date));
        System.out.println(newFormatter.format(new Date()));

        return "/tasksPage";
    }

//    @PostMapping("/tasks/delete")
//    public String deleteTask(){
//
//
//    }
//
//    @PostMapping("/tasks/edit")
//    public String editTask(){
//
//
//    }

    public static String dateFormat(String d){
        String year = d.substring(0,4);
        String dayMonth = d.substring(5);
        return dayMonth+"-"+year;
    }
}
