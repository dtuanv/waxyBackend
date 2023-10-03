package com.waxy.database.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    private Integer userInfoId;

    private Integer businessId;

    private Boolean isConfirmed;

    private Boolean isRejected;

    private String message;

    private String department;

}
