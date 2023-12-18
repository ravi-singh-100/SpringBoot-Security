package com.SpringSecurity.SpringSecurity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @GetMapping("/devloper")
    public String getDevloper(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication -> "+authentication);
        User user=  (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("username "+user.getUsername() +" password "+user.getPassword());
        User user1=  (User)SecurityContextHolder.getContext().getAuthentication().getCredentials();
        System.out.println(user1);
        return "Devloper";
    }
    @GetMapping("/tester")
    public String getTester(){
        return "Tester";
    }
    @GetMapping("/devops")
    public String devOps(){
        return "devOps";
    }
    @PostMapping("/devloper")
    public String postDevloper(){
        return "In Post Devloper";
    }
    @PostMapping("/sre")
    public String sre(){
        return "sre";
    }
}
