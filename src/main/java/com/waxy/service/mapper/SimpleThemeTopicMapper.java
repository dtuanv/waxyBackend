package com.waxy.service.mapper;

import com.waxy.database.dto.ThemeTopicDto;
import com.waxy.database.entity.ThemeTopic;
import com.waxy.database.repository.ThemeTopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleThemeTopicMapper extends ThemeTopicMapper{
    final ThemeTopicRepository themeTopicRepository;

    final TopicMapper topicMapper;
    @Override
    public ThemeTopic mapToEntity(ThemeTopicDto themeTopicDto) {
        ThemeTopic themeTopic;
        if(themeTopicDto.getId() > 0){
            themeTopic = themeTopicRepository
                    .findById(themeTopicDto.getId())
                    .orElseThrow(() -> new IllegalArgumentException(String.format("ThemeTopic can not be found by ID:  ",themeTopicDto.getId())));
        }else {
            themeTopic = new ThemeTopic();
        }

        themeTopic.setTopic(topicMapper.mapToEntity(themeTopicDto.getTopic()));

        themeTopic.setName(themeTopicDto.getName());


        return themeTopic;
    }

    @Override
    public ThemeTopicDto mapToDto(ThemeTopic themeTopic) {

        ThemeTopicDto themeTopicDto = new ThemeTopicDto();

        themeTopicDto.setId(themeTopic.getId());

        themeTopicDto.setTopic(topicMapper.mapToDto(themeTopic.getTopic()));

        themeTopicDto.setName(themeTopic.getName());
        return themeTopicDto;
    }
}
