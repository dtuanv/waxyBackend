package com.waxy.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EssentialLinkGroupDto {
    private Integer id;

    private String name;
    private String description;

    private List<EssentialLinkDto> essentialLinkDtoList;
}
