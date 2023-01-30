package com.project.taskit.controllers;

import com.project.taskit.models.Category;
import com.project.taskit.models.Task;
import com.project.taskit.models.User;
import com.project.taskit.repositories.CategoryRepository;
import com.project.taskit.repositories.TaskRepository;
import com.project.taskit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class TaskController {

    @Autowired
    UserRepository userDao;

    @Autowired
    CategoryRepository categoryDao;

    @Autowired
    TaskRepository taskDao;



    @GetMapping("/tasks")
    public String getTaskPage(Model model){
        List<Task> tasks = taskDao.findAll();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() == "anonymousUser")
        {
            return "redirect:login";
        }
        User loggedinUser =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getReferenceById(loggedinUser.getId());


        model.addAttribute("tasks",tasks);
        model.addAttribute("user", user);
        return "tasksPage";
    }

    @PostMapping("/tasks/create")
    public String createTask(Model model,
                             @RequestParam String action,
                             @RequestParam String completed,
                             @RequestParam String category,
                             @RequestParam(required = false, defaultValue = "null") String date) throws ParseException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() == "anonymousUser")
        {
            return "redirect:login";
        }
        User loggedinUser =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getReferenceById(loggedinUser.getId());

        System.out.println("Date: "+date);

        SimpleDateFormat newFormatter =new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);

        Category category1 = categoryDao.findByType(category);

        if(!date.equals("null")){
             date = dateFormat(date);
        }

        String createdDate = newFormatter.format(new Date());

        Task task = new Task(action, createdDate,date,completed,user,category1);

        System.out.println("I'm here");
        taskDao.save(task);

        return "redirect:/tasks";
    }


    @PostMapping("/tasks/complete/{id}")
    public String completeTask(@PathVariable long id){
        Task task = taskDao.getReferenceById(id);

        if(task.getCompleted().equalsIgnoreCase("incomplete")){
            task.setCompleted("complete");
        }else
            task.setCompleted("incomplete");

        taskDao.save(task);

        return "redirect:/tasks";
    }

    @PostMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id){
        taskDao.deleteById(id);
        return "redirect:/tasks";
    }
//
    @GetMapping("/tasks/edit/{id}")
    public String editTaskGet(@PathVariable Long id, Model model){
        Task task = taskDao.getReferenceById(id);
        List<Category> categories = categoryDao.findAll();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() == "anonymousUser")
        {
            return "redirect:login";
        }
        User loggedinUser =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getReferenceById(loggedinUser.getId());

        if(!task.getScheduledDate().equals("null")){
            task.setScheduledDate(dateFormatEdit(task.getScheduledDate()));
        }


        model.addAttribute("task",task);
        model.addAttribute("categories", categories);
        model.addAttribute("user",user);
//        model.addAttribute("date", date);
        return "editPage";
    }

    @PostMapping("/tasks/edit")
    public String editTaskPost(@RequestParam String action,
                               @RequestParam String completed,
                               @RequestParam String category,
                               @RequestParam(required = false, defaultValue = "null") String date,
                               @RequestParam long id){

        Task task = taskDao.getReferenceById(id);
        Category category1 = categoryDao.findByType(category);

        task.setAction(action);
        task.setCompleted(completed);
        task.setCategory(category1);
        task.setScheduledDate(dateFormat(date));

        System.out.println("Id: "+id);
        System.out.println("Task c date: "+ task.getDateCreated());
        System.out.println("Task s date: "+ date);
        System.out.println("Task category: "+ category);
        System.out.println("Task action: "+ action);

        taskDao.save(task);

        return "redirect:/tasks";
//        taskDao.save(task);
    }

    public static String dateFormat(String d){
        String year = d.substring(0,4);
        String dayMonth = d.substring(5);
        return dayMonth+"-"+year;
    }

    public static String dateFormatEdit(String d){
        String year = d.substring(6);
        String dayMonth = d.substring(0,5);
        return year+"-"+dayMonth;
    }
}
