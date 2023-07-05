package com.waxy.database.dto;

import com.waxy.database.entity.StaffItem;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class WorkplanDto {

    private long id;

    private String notification;

    private int workweek;

    private List<StaffItemDto> staffItemSet ;

    private int businessId;


    private AgendaDto agenda;


}
