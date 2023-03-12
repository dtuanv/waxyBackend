package com.waxy.service.mapper;

import com.waxy.database.dto.SentenceDto;
import com.waxy.database.entity.German;
import com.waxy.database.entity.Sentence;
import com.waxy.database.repository.GermanRepository;
import com.waxy.database.repository.SentenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SimpleSentenceMapper extends SentenceMapper{
    private final SentenceRepository sentenceRepository;
    private final GermanRepository germanRepository;

    private final TopicMapper topicMapper;

    private final GermanMapper germanMapper;

    private final ThemeTopicMapper themeTopicMapper;


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

        Set<German> germanSet = sentenceDto.getGermanDtoSet().stream().map(germanMapper :: mapToEntity).collect(Collectors.toSet());
        for ( German german : germanSet ) {
            german.setSentence(sentence);
        }
        sentence.setGermanSet(germanSet);


       sentence.setEnglish(sentenceDto.getEnglish());
       sentence.setVietnamese(sentenceDto.getVietnamese());

       sentence.setConjunction(sentenceDto.getConjunction());

       sentence.setTopic(topicMapper.mapToEntity(sentenceDto.getTopic()));

       if(sentenceDto.getThemeTopicDto() != null){
           sentence.setThemeTopic(themeTopicMapper.mapToEntity(sentenceDto.getThemeTopicDto()));

       }




        return sentence;
    }

    @Override
    public SentenceDto mapToDto(Sentence sentence) {
       SentenceDto sentenceDto = new SentenceDto();
       sentenceDto.setId(sentence.getId());
       sentenceDto.setEnglish(sentence.getEnglish());

      sentenceDto.setVietnamese(sentence.getVietnamese());

       sentenceDto.setGermanDtoSet(sentence.getGermanSet().stream().map(germanMapper :: mapToDto).collect(Collectors.toSet()));

        sentenceDto.setConjunction(sentence.getConjunction());
    if(
            sentence.getTopic() != null
    ){
        sentenceDto.setTopic(topicMapper.mapToDto(sentence.getTopic()));

    }
    if(sentence.getThemeTopic() != null){
        sentenceDto.setThemeTopicDto(themeTopicMapper.mapToDto(sentence.getThemeTopic()));
    }



//       SentenceDto
        return sentenceDto;
    }
}
