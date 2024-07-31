package com.waxy.service.mapper;

import com.waxy.dto.TopicDto;
import com.waxy.database.entity.Topic;

public abstract class TopicMapper {
    public abstract Topic mapToEntity(TopicDto topicDto);

    public abstract TopicDto mapToDto(Topic topic);

}
