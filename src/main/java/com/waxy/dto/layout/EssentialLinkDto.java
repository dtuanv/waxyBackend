package com.waxy.dto.layout;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
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


    public EssentialLinkDto(String title) {
        this.title = title;
    }
}
