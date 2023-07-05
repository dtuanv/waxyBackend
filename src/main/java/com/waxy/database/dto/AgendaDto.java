package com.waxy.database.dto;

import com.waxy.database.entity.StaffItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
@Getter
@Setter
public class AgendaDto {
    private long id;
    private List<StaffItemDto> mon;
    private List<StaffItemDto> tue;
    private List<StaffItemDto> wed;
    private List<StaffItemDto> thu;
    private List<StaffItemDto> fri;
    private List<StaffItemDto> sat;
    private List<StaffItemDto> sun;
}
