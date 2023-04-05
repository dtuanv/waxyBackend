package com.waxy.controller;

import com.waxy.database.dto.ThemeTopicDto;
import com.waxy.database.repository.ThemeTopicRepository;
import com.waxy.service.mapper.ThemeTopicMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

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
    @GetMapping("/themeTopics/topic/{topicId}")
    public Set<ThemeTopicDto> getThemeTopics(@PathVariable long topicId){
     return   themeTopicRepository.getAllThemeTopicByTopicId(topicId).stream()
                .map(themeTopicMapper :: mapToDto).collect(Collectors.toSet());
    }

    @GetMapping("/themeTopic/{themeId}")
    public ThemeTopicDto getThemeTopicById(@PathVariable long themeId){

        return themeTopicMapper.mapToDto(themeTopicRepository.findById(themeId).orElseThrow(() ->
                new IllegalArgumentException("can not found themeTopic By ID "+themeId))) ;
    }
}
