package com.waxy.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Workplan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade=CascadeType.ALL)
    private Agenda agenda;

    @Column(columnDefinition="TEXT")
    private String notification;

    private int workweek;

    private Integer businessId;
}
