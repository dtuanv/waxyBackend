package com.waxy.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class EssentialLinkDto {

    private Integer id;

    private String title;
    private String link;
    private String description;
    private String caption;
    private String icon;
    private Boolean isActive;
    private List<EssentialLinkDto> children = new ArrayList<>();
    private Integer essentialLinkGroupId;
}
