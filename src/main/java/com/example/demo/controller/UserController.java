package com.example.demo.controller;

import com.example.demo.model.Friend;
import com.example.demo.model.FriendRequest;
import com.example.demo.model.Message;
import com.example.demo.model.User;
import com.example.demo.repository.FriendRepository;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.RequestRepository;
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
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private FriendRepository friendRepository;

    @GetMapping("/messenger")
    public String messenger(ModelMap map){
        CurrentUser currentUser= (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        map.addAttribute("user0",new User());
        map.addAttribute("message0",new Message());
        map.addAttribute("currentUser",currentUser);
        map.addAttribute("allUser",userRepository.findAll());
        map.addAttribute("destination",friendRepository.lastFriend().getFriend());
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

    @GetMapping("/allUsersPage")
    public String allUsersPage(ModelMap map){
        map.addAttribute("allUsers",userRepository.findAll());
        return "allUsers";
    }

    @GetMapping("/sendRequest")
    public String sendRequest(@RequestParam("id") int id,@AuthenticationPrincipal UserDetails userDetails){
        FriendRequest request=new FriendRequest();
        request.setSource(userRepository.findOneByEmail(userDetails.getUsername()));
        request.setDestination(userRepository.findOne(id));
        requestRepository.save(request);
        return "redirect:/user/allUsersPage";
    }

    @GetMapping("/requestPage")
    public String requestPage(ModelMap map,@AuthenticationPrincipal UserDetails userDetails){
        User currentUser=userRepository.findOneByEmail(userDetails.getUsername());
        map.addAttribute("currentUser",currentUser);
        map.addAttribute("requestsToMe",requestRepository.findAllByDestination(currentUser));
        map.addAttribute("requestsFromMe",requestRepository.findAllBySource(currentUser));
        return "requestPage";
    }

    @GetMapping("/deleteRequest")
    public String deleteRequest(@RequestParam("id") int id,@AuthenticationPrincipal UserDetails userDetails){
        requestRepository.deleteOurRequest(userRepository.findOneByEmail(userDetails.getUsername()).getId(),id,userRepository.findOneByEmail(userDetails.getUsername()).getId(),id);
        return "redirect:/user/requestPage";
    }

    @GetMapping("/confirmRequest")
    public String confirmRequest(@RequestParam("id") int id,@AuthenticationPrincipal UserDetails userDetails){
        Friend friend=new Friend();
        friend.setMyself(userRepository.findOneByEmail(userDetails.getUsername()));
        friend.setFriend(userRepository.findOne(id));
        friendRepository.save(friend);
        requestRepository.deleteOurRequest(userRepository.findOneByEmail(userDetails.getUsername()).getId(),id,userRepository.findOneByEmail(userDetails.getUsername()).getId(),id);
        return "redirect:/user/requestPage";
    }

    @GetMapping("/friendsPage")
    public String friendsPage(ModelMap map,@AuthenticationPrincipal UserDetails userDetails){
        map.addAttribute("myFriends",friendRepository.findAllByMyself(userRepository.findOneByEmail(userDetails.getUsername())));
        map.addAttribute("myself",userRepository.findOneByEmail(userDetails.getUsername()));
        return "myFriends";
    }

    @GetMapping("/deleteFriend")
    public String deleteFriend(@RequestParam("id") int id,@AuthenticationPrincipal UserDetails userDetails){
        friendRepository.deleteMyFriend(userRepository.findOneByEmail(userDetails.getUsername()).getId(),id,userRepository.findOneByEmail(userDetails.getUsername()).getId(),id);
        return "redirect:/user/friendsPage";
    }

    @GetMapping("/myPage")
    public String myPage(ModelMap map,@AuthenticationPrincipal UserDetails userDetails){
        map.addAttribute("myself",userRepository.findOneByEmail(userDetails.getUsername()));
        map.addAttribute("myFriends",friendRepository.findAllByMyself(userRepository.findOneByEmail(userDetails.getUsername())));
        return "myPage";
    }

    @GetMapping("/singlUserPage/{id}")
    public String singlUserPage(ModelMap map,@AuthenticationPrincipal UserDetails userDetails,@PathVariable("id") int id){
        map.addAttribute("currentUser",userRepository.findOneByEmail(userDetails.getUsername()));
        map.addAttribute("thisUser",userRepository.findOne(id));
        return "singlUserPage";
    }
}
