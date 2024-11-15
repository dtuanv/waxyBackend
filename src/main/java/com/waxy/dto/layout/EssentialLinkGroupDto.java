package com.waxy.dto.layout;

import com.waxy.dto.layout.EssentialLinkDto;
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

    public EssentialLinkGroupDto(String name, String description){
        this.name = name;
        this.description = description;
    }

}
