package com.waxy.controller;

import com.waxy.database.dto.ThemeTopicDto;
import com.waxy.database.repository.ThemeTopicRepository;
import com.waxy.service.mapper.ThemeTopicMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class ThemeTopicController {
    final ThemeTopicRepository themeTopicRepository;



    final ThemeTopicMapper themeTopicMapper;


    @PostMapping("/saveThemeTopic")
    public void saveThemeTopic(@RequestBody ThemeTopicDto themeTopicDto){
        themeTopicRepository.save(themeTopicMapper.mapToEntity(themeTopicDto));
    }
//
//    @GetMapping("/themeTopics/topic/{topicId}")
//    public Set<ThemeTopicDto> getThemeTopics(@PathVariable long topicId){
//        themeTopicRepository.fin
//    }
}
