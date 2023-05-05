package com.waxy.database.dto;

import com.waxy.database.entity.Topic;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
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
