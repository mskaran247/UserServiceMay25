package com.example.userservicemay25.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.metamodel.mapping.internal.EntityCollectionPart;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity(name = "Tokens")
public class Tokens extends BaseModel{
    private String value;

    @ManyToAny
    private User user;
    private LocalDateTime expiryDateTime;


}
/*
Cardinality

1             M
User   ---   Token
1              1

 */
