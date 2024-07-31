package com.waxy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffItemDto {
    private long id;

    private String title;

    private int indexDay;

    private int staffId;

    private String time;

    private String department;
}
