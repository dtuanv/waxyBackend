package com.waxy.controller;

import com.waxy.database.dto.SentenceDto;
import com.waxy.database.entity.German;
import com.waxy.database.entity.Sentence;
import com.waxy.database.repository.GermanRepository;
import com.waxy.database.repository.SentenceRepository;
import com.waxy.service.mapper.GermanMapper;
import com.waxy.service.mapper.SentenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    private void saveSentence(@RequestBody Sentence sentence){

        for (German german : sentence.getGermanSet() ) {
            german.setSentence(sentence);
            germanRepository.save(german);
        }
       sentenceRepository.save(sentence);
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

}
