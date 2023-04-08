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
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition="TEXT")
    private String name;

    private String category;

    @OneToMany(mappedBy = "topic",cascade = CascadeType.ALL)
    private Set<Sentence> sentences;

    @OneToMany(mappedBy = "topic",cascade = CascadeType.ALL)
    private Set<ThemeTopic> themeTopics;

    private Long userInfoId;
}
