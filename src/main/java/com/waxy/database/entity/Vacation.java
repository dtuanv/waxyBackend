package com.waxy.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vacation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String details;

    private String start;

    private String toDate;

    private String bgcolor;

    private String time;

    private Integer duration;

    private String icon;

    private Integer userId;

    private Integer businessId;

}
