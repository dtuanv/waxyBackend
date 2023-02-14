package com.waxy.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Sentence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String english;

    private String vietnamese;

    @OneToMany(mappedBy = "sentence", cascade = CascadeType.ALL)
    private Set<German> germanSet;
}
