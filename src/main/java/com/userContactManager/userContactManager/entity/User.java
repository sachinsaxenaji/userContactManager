package com.userContactManager.userContactManager.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private  int id;
    @NotBlank(message = "name field is require")
    @Size(min =  2, max = 20, message = "min 2 & max 20 character allowed")
    private String name;

    @Column(unique = true)

    private String email;
    private String role;
    private String password;
    private String imageUrl;

    @Column(length = 5000)
    private String about;
    
    private boolean enabled ;
    

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contact> list = new ArrayList<Contact>();

    public List<Contact> getList() {
        return list;
    }

    public void setList(List<Contact> list) {
        this.list = list;
    }

    public User(int id, String name, String email, String role, String password, String imageUrl, String about, boolean enabled) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
        this.imageUrl = imageUrl;
        this.about = about;
        this.enabled = enabled;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getimageUrl() {
        return imageUrl;
    }

    public void setimageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +

                ", imageUrl='" + imageUrl + '\'' +
                ", about='" + about + '\'' +
                ", enabled=" + enabled +
                ", list=" + list +
                '}';
    }
}
