package com.waxy.service.mapper;

import com.waxy.dto.TopicDto;
import com.waxy.database.entity.Topic;
import com.waxy.database.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SimpleTopicMapper extends TopicMapper{
    final TopicRepository topicRepository;


    @Override
    public Topic mapToEntity(TopicDto topicDto) {
        Topic topic;
        if(topicDto.getId() > 0){
        topic = topicRepository.findById(topicDto.getId()).orElseThrow(() -> new IllegalArgumentException(
                String.format("Topic can not be found  By ID: "+topicDto.getId())
        ));
        }else {
            topic = new Topic();
        }
        topic.setName(topicDto.getName());

        topic.setCategory(topicDto.getCategory());

        topic.setUserInfoId(topicDto.getUserInfoId());
        return topic;
    }

    @Override
    public TopicDto mapToDto(Topic topic) {
        TopicDto topicDto = new TopicDto();

        topicDto.setId(topic.getId());

        topicDto.setName(topic.getName());

        topicDto.setCategory(topic.getCategory());

        topicDto.setUserInfoId(topic.getUserInfoId());

        return topicDto;
    }
}
