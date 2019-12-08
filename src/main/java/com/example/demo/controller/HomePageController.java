package com.example.demo.controller;

import com.example.demo.Model.AppUser;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class HomePageController {

    @Autowired
    UserRepository userRepository;

    List<AppUser> appUsers;

    AppUser appUser;

    private  String url = "output.jpg";

    @GetMapping("/homePage")
    public String goHome(Model model, Principal principal){
        appUsers = new ArrayList<>();
        userRepository.findAll().forEach(appUsers::add);
        model.addAttribute("allUsers", appUsers);
        return "home";
    }


    @GetMapping("/userInfo")
    public String goProfile(Model model, Principal principal) throws IOException {
        String userName = principal.getName();
        appUser = userRepository.findByUserName(userName);


  /*      if(appUser.getPicture()!=null){
            byte[] mypic = appUser.getPicture();
            try {
                FileOutputStream fos = new FileOutputStream(url);
                fos.write(mypic);
                fos.close();

                model.addAttribute("source",url);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
   */
        if(appUser.getPicture()!=null) {
            byte[] mypic = appUser.getPicture();
            Files.write(Paths.get(url),mypic);
            model.addAttribute("source",url);
        }
        model.addAttribute("guest", appUser);
        return "profile";
    }

    @PostMapping("/dataUpdate")
    public String singlePhotUpload(@RequestParam("img") MultipartFile file, RedirectAttributes redirectAttributes,
            @RequestParam("userName") String uname, @RequestParam("fullName") String fname,
                                   Model model){

        if(!file.isEmpty()){
            //redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            try {
                byte[] pic = file.getBytes();
                appUser.setPicture(pic);

                Date date = new Date();
                appUser.setPhotoDate(date);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        appUser.setUserName(uname);
        appUser.setFullName(fname);

        //updates the data
        userRepository.save(appUser);

        model.addAttribute("message", "Successfully Updated");
        model.addAttribute("guest", appUser);

        if(appUser.getPicture()!=null) {
            byte[] mypic = appUser.getPicture();
            try {
                Files.write(Paths.get(url),mypic);
            } catch (IOException e) {
                e.printStackTrace();
            }
            model.addAttribute("source",url);
        }

        return "profile";
    }

}
