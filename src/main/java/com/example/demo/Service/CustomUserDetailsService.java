package com.example.demo.Service;

import com.example.demo.Model.AppUser;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser =userRepository.findByUserName(username);
        CustomUserDetails userDetails = null;
        if(appUser!=null){
            userDetails = new CustomUserDetails();
            userDetails.setAppUser(appUser);
        }
        else {
            throw new UsernameNotFoundException(appUser+" is not found");
        }
        return userDetails;
    }

}
