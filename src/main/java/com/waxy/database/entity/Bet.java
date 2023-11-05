package com.waxy.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String  firstSelected;
    private String  secondSelected;
    private String  thirdSelected;
    private String  name;
    private String  name1;
    private String  name2;
    private String  name3;
    private String  name4;
    private String  name5;
    private String  name6;

    private double amount;

    private double profit;

    @ManyToOne
    BetGroup betGroup;
}
