package com.waxy.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Workplan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="workplan_id")
//    private Set<StaffItem> staffItemSet = new LinkedHashSet<>();
    private List<StaffItem> staffItemSet = new ArrayList<>();


    @Column(columnDefinition="TEXT")
    private String notification;

    private int workweek;

    private Integer businessId;
}
