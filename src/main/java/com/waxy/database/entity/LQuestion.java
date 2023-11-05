package com.waxy.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class LQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int number;

    private String label;

    private String myAnswer;

    private String vi;

    private String ans;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<LAnswerOption> answerOptions;
}
