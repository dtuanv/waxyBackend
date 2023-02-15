package com.waxy.controller;

import com.waxy.database.dto.TopicDto;
import com.waxy.database.entity.Topic;
import com.waxy.database.repository.TopicRepository;
import com.waxy.service.mapper.TopicMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TopicController {

    final TopicRepository topicRepository;
    final TopicMapper topicMapper;

    @PostMapping("/saveTopic")
    private void saveTopic(@RequestBody TopicDto topicDto){

        topicRepository.save(topicMapper.mapToEntity(topicDto));
    }

    @GetMapping("/allTopic")
    private List<TopicDto> getAllTopic(){
        return topicRepository.findAll().stream().map(topicMapper :: mapToDto).collect(Collectors.toList());
    }

    @GetMapping("/topicBy/{topicId}")
    private TopicDto getTopic(@PathVariable long topicId){
        return topicMapper.mapToDto(topicRepository.findById(topicId).orElseThrow(() -> new IllegalArgumentException(String.format(
             "Topic can be not found ID: "+ topicId
        ))));
    }
}
