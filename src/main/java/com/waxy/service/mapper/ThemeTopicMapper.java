package com.waxy.service.mapper;

import com.waxy.dto.ThemeTopicDto;
import com.waxy.database.entity.ThemeTopic;

public abstract class ThemeTopicMapper{

    public abstract ThemeTopic mapToEntity(ThemeTopicDto themeTopicDto);

    public abstract ThemeTopicDto mapToDto(ThemeTopic themeTopic);
}
