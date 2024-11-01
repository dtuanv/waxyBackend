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
public class ThemeTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;


    @ManyToOne
    private Topic topic;

    @OneToMany(mappedBy = "themeTopic",cascade = CascadeType.ALL)
    Set<Sentence> sentenceSet;
}
