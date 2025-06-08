package com.example.userservicemay25.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "Tokens")
public class Token extends BaseModel{
    private String value;

    @ManyToOne
    private User user;
    private LocalDateTime expiryDateTime;


}
/*
Cardinality

1             M
User   ---   Token
1              1

 */
