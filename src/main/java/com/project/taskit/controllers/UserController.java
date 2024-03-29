package com.project.taskit.controllers;


import com.project.taskit.models.User;
import com.project.taskit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    @Autowired
    private UserRepository userDao;


    @Autowired
    PasswordEncoder passwordEncoder;

//    public UserController(PasswordEncoder passwordEncoder){
//        this.passwordEncoder = passwordEncoder;
//    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "user/signupPage";
    }

    @PostMapping("/sign-up")
    public String saveUser(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model, RedirectAttributes attributes) {

        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(email);
        System.out.println(username);
        System.out.println(password);


        //New User object
        User user = new User(firstName, lastName, username,email,password,"null");



        if (!isValidPassword(user.getPassword())) {
            model.addAttribute("firstname", user.getFirstName());
            model.addAttribute("lastname", user.getLastName());
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());

            model.addAttribute("invalid", "Password must be 8 characters and above and must contain a special character");
            return "user/signupPage";
        }

        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setProfileImage("https://cdn.filestackcontent.com/SftfgsETQmEGDT0gfjsq");
        userDao.save(user);
        attributes.addFlashAttribute("success", "You successfully registered! You can now login");
        return "redirect:/login";
    }



    public boolean isValidPassword(String password) {
        return password.length() >= 8 &&
                password.contains("!") ||
                password.contains("#") ||
                password.contains("$") ||
                password.contains("%") ||
                password.contains("&") ||
                password.contains("*") ||
                password.contains("-") ||
                password.contains("_") ||
                password.contains("(") ||
                password.contains("+") ||
                password.contains(")") ||
                password.contains("=") ||
                password.contains("{") ||
                password.contains("}") ||
                password.contains("[") ||
                password.contains("]") ||
                password.contains(":") ||
                password.contains(";") ||
                password.contains("\"") ||
                password.contains("<") ||
                password.contains(">") ||
                password.contains(".") ||
                password.contains("?") ||
                password.contains("@") ||
                password.contains("\\") ||
                password.contains("'");

    }

    @GetMapping("/users/imageUpload/{baseImgUrl}/{extensionUrl}/{returnUrl}")
    public String profileImageUpload(@PathVariable String baseImgUrl,
                                     @PathVariable String extensionUrl,
                                     @PathVariable String returnUrl) {
        System.out.println("Upload image controller hit");
        System.out.println("base image url: " + baseImgUrl);
        System.out.println("extension url: " + extensionUrl);
        System.out.println("return url: " + returnUrl);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() == "anonymousUser") {
            return "redirect:login";
        }
        User loggedinUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getReferenceById(loggedinUser.getId());

        String imgUrl = "https://" + baseImgUrl + "/" + extensionUrl;
        user.setProfileImage(imgUrl);
        userDao.save(user);
        System.out.println("Save should hit");
        return "redirect:/tasks" ;
    }
}
