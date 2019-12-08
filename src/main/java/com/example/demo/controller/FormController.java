package com.example.demo.controller;

import com.example.demo.Model.AppUser;
import com.example.demo.Model.UserFormData;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class FormController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Value("${error.message}")
    String errorMessage;

    @RequestMapping("/login")
    public String loginPage(Model model, String error, String logout) {
        if(error !=null){
            model.addAttribute("message", "Your username and password is invalid");
        }
        if(logout!=null){
            model.addAttribute("message", "You have been logged out");
        }
        return "login";
    }

    @GetMapping("/register")
    public String regPage(Model model) {
        UserFormData user = new UserFormData();
        model.addAttribute("userdata", user);
        return "register";
    }

    @PostMapping("/register")
    public String addMember(Model model, @ModelAttribute("userdata") @Valid UserFormData userdata,
                            BindingResult bindingResult) {

        String username = userdata.getUserName();
        String fullname = userdata.getFullName();
        String password = userdata.getPassword();
        String confirm  = userdata.getConfirmPassword();


        if(!password.equals(confirm)){
            model.addAttribute("errorMessage", "Password doesnt match with Confirm");
            return "register";
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("errorMessage", errorMessage);
            return "register";
        }

        if(username!=null&&username.length()>0
                &&password!=null&&password.length()>0
                &&fullname!=null&&fullname.length()>0){

            String enc_pwd = encoder.encode(password);
            AppUser user = new AppUser(username,fullname,enc_pwd);

            Date date = new Date();
            user.setRegistrationDate(date);

            userRepository.save(user);
            model.addAttribute("message", "Successfully Registered");

            return "redirect:/login";
        }
        else {
            model.addAttribute("errorMessage", errorMessage);
            return "register";
        }
    }
}
