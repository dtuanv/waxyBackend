package com.waxy.service.mapper;

import com.waxy.database.entity.Topic;
import com.waxy.dto.TopicDto;

public abstract class TopicMapper {
    public abstract Topic mapToEntity(TopicDto topicDto);

    public abstract TopicDto mapToDto(Topic topic);

}
