package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/admin")
    public String adminPage(ModelMap map, @AuthenticationPrincipal UserDetails userDetails){
        User admin=userRepository.findOneByEmail(userDetails.getUsername());
        map.addAttribute("messageForAdmin",String.format("Hi or dear %s", admin.getName()));
        map.addAttribute("allUsers",userRepository.findAll());
        return "adminPage";
    }

    @GetMapping("/deleteUserById")
    public String deleteUser(@RequestParam("id") int id){
        userRepository.delete(id);
        return "redirect:/admin/admin";
    }



}
