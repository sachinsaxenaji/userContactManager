package com.userContactManager.userContactManager.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.userContactManager.userContactManager.validation.EmailWithTld;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Contact")
public class Contact {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cId;
    @NotBlank
    private String name;


    @EmailWithTld
    private String email;
    private String secondName;
    private String work;
    private String phone;
    private String image;

    @Column(length = 1000)
    private String description;


    @ManyToOne
    @JsonIgnore
    private  User user;


    public Contact(int cId, String name, String email, String secondName, String work, String phone, String image, String description) {
        this.cId = cId;
        this.name = name;
        this.email = email;
        this.secondName = secondName;
        this.work = work;
        this.phone = phone;
        this.image = image;
        this.description = description;

    }

    public Contact() {
    }




    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public boolean equals(Object obj) {
        return this.cId == ((Contact)obj).getcId();
    }


}
