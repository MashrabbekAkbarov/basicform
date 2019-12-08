package com.example.demo.controller;

import com.example.demo.Model.AppUser;
import com.example.demo.Model.UserFormData;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Validator.AppUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    private AppUserValidator appUserValidator;

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder){
        Object target = dataBinder.getTarget();
        if(target==null) return;
        if(target.getClass()==UserFormData.class) dataBinder.setValidator(appUserValidator);
    }

    @RequestMapping("/")
    public String indexPage(){
        return "login";
    }

}
