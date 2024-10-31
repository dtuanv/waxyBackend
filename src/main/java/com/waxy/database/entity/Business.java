package com.waxy.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String owner;

    private String address;

    private String status;

    private Integer numberUsers;

    private String businessArea;

    public Business(long id, String name, String owner, String address, String status, Integer numberUsers, String businessArea) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.address = address;
        this.status = status;
        this.numberUsers = numberUsers;
        this.businessArea = businessArea;
    }
}
