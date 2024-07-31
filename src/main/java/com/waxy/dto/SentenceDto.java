package com.waxy.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class SentenceDto {

    private long id;


    private String english;

    private String vietnamese;

    private Set<GermanDto> germanDtoSet;

    private String conjunction;

    private TopicDto topic;

    private ThemeTopicDto themeTopicDto;

    private LocalDate updateAt;
}
