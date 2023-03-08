package com.waxy.database.dto;

import com.waxy.database.entity.Topic;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThemeTopicDto {
    private long id;

    private String name;

    private Topic topic;

}
