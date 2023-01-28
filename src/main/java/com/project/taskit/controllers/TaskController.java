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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        model.addAttribute("tasks",tasks);
        return "tasksPage";
    }

    @PostMapping("/tasks/create")
    public String createTask(Model model,
                             @RequestParam String action,
                             @RequestParam String completed,
                             @RequestParam String category,
                             @RequestParam String date) throws ParseException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() == "anonymousUser")
        {
            return "redirect:login";
        }
        User loggedinUser =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getReferenceById(loggedinUser.getId());

        System.out.println(date);

        SimpleDateFormat newFormatter =new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);

        Category category1 = categoryDao.findByType(category);

        String scheduledDate = dateFormat(date);
        String createdDate = newFormatter.format(new Date());

        Task task = new Task(action, createdDate,scheduledDate,completed,user,category1);

        taskDao.save(task);

        return "redirect:/tasks";
    }

    @PostMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id){
        taskDao.deleteById(id);
        return "redirect:/tasks";
    }
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
