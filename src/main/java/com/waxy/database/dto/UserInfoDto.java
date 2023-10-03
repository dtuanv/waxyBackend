package com.waxy.database.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class UserInfoDto {

    private long id;

    private String name;


    private String avatar;

    private long userId;

    private String role;

    private Integer restVacation;

    private Long businessId;

    private String businessArea;

    private String department;

    private String birthday;

    private String position;

    private String language;

    private String businessName;

    private boolean firstLogin;

}
