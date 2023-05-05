package com.waxy.controller;

import com.waxy.database.dto.SentenceDto;
import com.waxy.database.entity.German;
import com.waxy.database.entity.Sentence;
import com.waxy.database.repository.GermanRepository;
import com.waxy.database.repository.SentenceRepository;
import com.waxy.service.mapper.GermanMapper;
import com.waxy.service.mapper.SentenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class SentenceController {
    private final SentenceRepository sentenceRepository;

    private final SentenceMapper sentenceMapper;
    final GermanMapper germanMapper;

    private final GermanRepository germanRepository;


    @PostMapping("/saveSentence")
    private void saveSentence(@RequestBody SentenceDto sentenceDto){

//        for (German german : sentence.getGermanSet() ) {
//            german.setSentence(sentence);
//            germanRepository.save(german);
//        }
       sentenceRepository.save(sentenceMapper.mapToEntity(sentenceDto));
    }

    @GetMapping("/allSentence")
    private Set<SentenceDto> getAllSentence(){
        Set<SentenceDto> sentenceDtoSet =
       sentenceRepository.findAll().stream().map(sentenceMapper :: mapToDto).collect(Collectors.toSet());

        for (SentenceDto sentenceDto : sentenceDtoSet ) {
           sentenceDto.setGermanDtoSet(germanRepository
                    .findGermanBySentenceId(sentenceDto.getId()).stream().map(germanMapper :: mapToDto).collect(Collectors.toSet()));


        }

        return sentenceDtoSet;
    }


    @GetMapping("/sentence/topic/{topicId}")
    private Set<SentenceDto> getSentenceByTopic(@PathVariable long topicId){
        Set<SentenceDto> sentenceDtoSet =
                sentenceRepository.getSentenceByTopicId(topicId).stream().map(sentenceMapper :: mapToDto).collect(Collectors.toSet());

        for (SentenceDto sentenceDto : sentenceDtoSet ) {
            sentenceDto.setGermanDtoSet(germanRepository
                    .findGermanBySentenceId(sentenceDto.getId()).stream().map(germanMapper :: mapToDto).collect(Collectors.toSet()));


        }

        return sentenceDtoSet;
    }

    @GetMapping("/sentenceBy/{sentenceId}")
    public SentenceDto getSentenceBy(@PathVariable long sentenceId){
            SentenceDto sentenceDto = sentenceMapper.mapToDto(sentenceRepository.findById(sentenceId).orElseThrow(()
                    -> new IllegalArgumentException(String.format(
                    "can not found Sentence BY ID : "+ sentenceId
            ))));
            return sentenceDto;
    }

    @DeleteMapping("/delete/sentence/{sentenceId}")
    private void deleteSentence(@PathVariable Long sentenceId){

        sentenceRepository.deleteById(sentenceId);
    }

    @GetMapping("/sentence/week/id/{userInfoId}")
    private Set<SentenceDto> getSentenceInWeek(@PathVariable long userInfoId){
        Set<SentenceDto> sentenceDtoSet = sentenceRepository.findSentenceInWeekByUserInfoId(userInfoId)
                .stream().map(sentenceMapper :: mapToDto).collect(Collectors.toSet());
        return sentenceDtoSet;
    }

}
