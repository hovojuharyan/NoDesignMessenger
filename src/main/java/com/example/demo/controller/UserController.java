package com.example.demo.controller;

import com.example.demo.model.Message;
import com.example.demo.model.User;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/messenger")
    public String messenger(ModelMap map){
        CurrentUser currentUser= (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        map.addAttribute("user0",new User());
        map.addAttribute("message0",new Message());
        map.addAttribute("currentUser0",currentUser);
        map.addAttribute("allUser",userRepository.findAll());
        return "messenger";
    }

    @GetMapping("/messageSingl/{id}")
    public String messageSingl(@PathVariable("id") int id, ModelMap map, @AuthenticationPrincipal UserDetails userDetails){
        CurrentUser currentUser= (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        map.addAttribute("destination",userRepository.findOne(id));
        map.addAttribute("currentUser",currentUser);
        map.addAttribute("message0",new Message());
        map.addAttribute("allUser",userRepository.findAll());
        map.addAttribute("ourMessages",messageRepository.ourMessages(userRepository.findOneByEmail(userDetails.getUsername()).getId(),id,userRepository.findOneByEmail(userDetails.getUsername()).getId(),id));
        return "messenger";
    }

    @PostMapping("/writeMessage/{id}")
    public String writeMessage(@PathVariable("id") int id, @ModelAttribute("message0") Message message,@AuthenticationPrincipal UserDetails userDetails){
        message.setSource(userRepository.findOneByEmail(userDetails.getUsername()));
        message.setDestination(userRepository.findOne(id));
        messageRepository.save(message);
        return "redirect:/user/messageSingl/"+id;
    }


}
