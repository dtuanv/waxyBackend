package com.waxy.database.dto;

import com.waxy.database.entity.StaffItem;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class WorkplanDto {

    private long id;

    private String notification;

    private int workweek;

    private Set<StaffItemDto> staffItemSet;

    private int businessId;


}
