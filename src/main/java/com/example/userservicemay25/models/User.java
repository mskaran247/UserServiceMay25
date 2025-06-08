package com.example.userservicemay25.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "Users")
public class User extends BaseModel{
    private String name;

    @Column(unique = true)
    private String email;
    private String password;

    @ManyToMany
    private List<Role> roles;



}

/*
Cardinatliy

1              M
User          Role
M              1

 */