package com.example.demo.Model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Lob
    @Column(name = "img")
    private byte[] picture;

    @Column(name = "reg_date")
    private Date registrationDate;

    @Column(name = "img_date")
    private Date photoDate;

    public AppUser(){
    }

    public AppUser(String userName, String fullName, String password){
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Nullable
    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(@Nullable byte[] picture) {
        this.picture = picture;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getPhotoDate() {
        return photoDate;
    }

    public void setPhotoDate(Date photoDate) {
        this.photoDate = photoDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

