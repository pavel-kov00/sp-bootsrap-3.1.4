package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public AdminController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String getUsers(Model model){
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "users";
    }


    @GetMapping("/add")
    public String addUser(Model model) {
        List<Role> roles = roleService.getallrole();
        model.addAttribute("allroles",roles);
//        model.addAttribute("user",new User());
        return "adduser";
    }

    @PostMapping("/add")
    public String addUserPost(@ModelAttribute("user") User user){
        userService.addUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") long id, Model model){
        List<Role> roles = roleService.getallrole();
        model.addAttribute("allroles",roles);
        model.addAttribute("user",userService.getUser(id));
        return "edit";
    }

    @PatchMapping("/edit/{id}")
    public String updateUserPost(@ModelAttribute User user,@PathVariable("id") long id){
        userService.updateUser(user,id);
        System.out.println(user.getRoles());
        return "redirect:/admin/";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin/";
    }
}
