package com.waxy.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
