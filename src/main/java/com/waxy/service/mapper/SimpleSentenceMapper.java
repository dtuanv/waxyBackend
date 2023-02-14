package com.waxy.service.mapper;

import com.waxy.database.dto.SentenceDto;
import com.waxy.database.entity.Sentence;
import com.waxy.database.repository.GermanRepository;
import com.waxy.database.repository.SentenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SimpleSentenceMapper extends SentenceMapper{
    private final SentenceRepository sentenceRepository;
    private final GermanRepository germanRepository;

    private final GermanMapper germanMapper;


    @Override
    public Sentence mapToEntity(SentenceDto sentenceDto) {
//        System.out.println("SentenceDto.getId() "+SentenceDto.getId());
//       SentenceSentence;
        Sentence  sentence;
        if( sentenceDto.getId() > 0){
           sentence =sentenceRepository.findById(sentenceDto.getId()).orElseThrow(()
                    -> new IllegalArgumentException(String.format("can not findSentence! ID: "+sentenceDto.getId())));
        }else {
           sentence = new Sentence();

        }


       sentence.setEnglish(sentenceDto.getEnglish());
       sentence.setVietnamese(sentenceDto.getVietnamese());




        return sentence;
    }

    @Override
    public SentenceDto mapToDto(Sentence sentence) {
       SentenceDto sentenceDto = new SentenceDto();
       sentenceDto.setId(sentence.getId());
       sentenceDto.setEnglish(sentence.getEnglish());

      sentenceDto.setVietnamese(sentence.getVietnamese());

       sentenceDto.setGermanDtoSet(sentence.getGermanSet().stream().map(germanMapper :: mapToDto).collect(Collectors.toSet()));





//       SentenceDto
        return sentenceDto;
    }
}
