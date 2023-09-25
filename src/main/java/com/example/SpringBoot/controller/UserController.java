package com.example.SpringBoot.controller;

import com.example.SpringBoot.model.User;
import com.example.SpringBoot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/user-create")
    public String createNewUser(Model model) {
        model.addAttribute("user", new User());
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable Long id, Model model) {
        model.addAttribute("update", userService.getUserById(id));
        return "updateUser";
    }

    @PostMapping("/updateUser/{getId}")
    public String saveUpdateUser(@PathVariable Long getId, @ModelAttribute("user") User user) {
        user.setId(getId);
        userService.updateUser(user);
        return "redirect:/";
    }
}