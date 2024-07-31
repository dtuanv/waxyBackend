package com.waxy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThemeTopicDto {
    private long id;

    private String name;

    private TopicDto topic;

}
