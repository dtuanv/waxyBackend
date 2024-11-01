package com.waxy.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class BetDto {
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

    @Column(name="amount")
    private double amount;

    @Column(name="profit")
    private double profit;

}
