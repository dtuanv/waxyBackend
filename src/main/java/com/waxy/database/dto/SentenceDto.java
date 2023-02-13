package com.waxy.database.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SentenceDto {

    private Integer id;

    private String english;

    private String vietnamese;

    private Set<GermanDto> germanDtoSet;
}
