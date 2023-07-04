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
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany(mappedBy = "agenda",cascade = CascadeType.MERGE)
    private Set<StaffItem> mon;

    @OneToMany(mappedBy = "agenda",cascade = CascadeType.MERGE)
    private Set<StaffItem> tue;

    @OneToMany(mappedBy = "agenda",cascade = CascadeType.MERGE)
    private Set<StaffItem> wed;

    @OneToMany(mappedBy = "agenda",cascade = CascadeType.MERGE)
    private Set<StaffItem> thu;

    @OneToMany(mappedBy = "agenda",cascade = CascadeType.MERGE)
    private Set<StaffItem> fri;

    @OneToMany(mappedBy = "agenda",cascade = CascadeType.MERGE)
    private Set<StaffItem> sat;

    @OneToMany(mappedBy = "agenda",cascade = CascadeType.MERGE)
    private Set<StaffItem> sun;

}
