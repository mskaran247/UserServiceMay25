package com.example.userservicemay25.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Roles")
public class Role extends BaseModel {
    private String value;

}
