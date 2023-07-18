package com.waxy.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.sql.In;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String avatar;

    private long userId;

    private String role;

    private Integer restVacation;

    private Long businessId;

    private String department;

    private String birthday;

    private String position;
}
