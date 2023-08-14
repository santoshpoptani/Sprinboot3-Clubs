package com.example.springboot.controller;

import com.example.springboot.dto.RegistrationDto;
import com.example.springboot.models.UserEntity;
import com.example.springboot.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private UserServices userServices;

    @Autowired
    public AuthController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model){
        RegistrationDto registrationDto = new RegistrationDto();
        model.addAttribute("user",registrationDto);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDto registrationDto, BindingResult result,Model model)
    {
        UserEntity existingUser = userServices.findByEmail(registrationDto.getEmail());
        if(existingUser !=null && existingUser.getEmail()!=null && !existingUser.getEmail().isEmpty()){
          return "redirect:/register?fail";

        }
        UserEntity existingUserWithUsername = userServices.findByUserName(registrationDto.getUsername());
        if(existingUserWithUsername !=null && existingUserWithUsername.getUsername()!=null && !existingUserWithUsername.getUsername().isEmpty()){
           return "redirect:/register?fail";

        }
        if(result.hasErrors()){
            model.addAttribute("user",registrationDto);
            return "register";
        }
        userServices.saveUsers(registrationDto);
        return "redirect:/clubs?success";
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
