package com.example.demo.Repository;

import com.example.demo.Model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Integer> {
    AppUser findByUserName(String username);
}
