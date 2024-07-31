package com.waxy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicDto {

    private long id;

    private String name;

    private String category;

    private Long userInfoId;
}
