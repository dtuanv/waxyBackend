package com.waxy.database.dto;

import com.waxy.database.entity.StaffItem;

import java.util.Set;

public class AgendaDto {
    private long id;
    private Set<StaffItem> mon;
    private Set<StaffItem> tue;
    private Set<StaffItem> wed;
    private Set<StaffItem> thu;
    private Set<StaffItem> fri;
    private Set<StaffItem> sat;
    private Set<StaffItem> sun;
}
