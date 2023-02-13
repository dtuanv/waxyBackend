package com.waxy.service.mapper;

import com.waxy.database.dto.SentenceDto;
import com.waxy.database.entity.Sentence;
import com.waxy.database.repository.GermanRepository;
import com.waxy.database.repository.SentenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleSentenceMapper extends SentenceMapper{
    private final SentenceRepository sentenceRepository;
    private final GermanRepository germanRepository;


    @Override
    public Sentence mapToEntity(SentenceDto sentenceDto) {
//        System.out.println("SentenceDto.getId() "+SentenceDto.getId());
//       SentenceSentence;
//        if(SentenceDto.getId() != null &&SentenceDto.getId() > 0){
//           Sentence =SentenceRepository.findById(SentenceDto.getId()).orElseThrow(()
//                    -> new IllegalArgumentException(String.format("can not findSentence! ID: "+SentenceDto.getId())));
//        }else {
//           Sentence = newSentence();
//
//        }
       Sentence sentence = new Sentence();

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

//       SentenceDto.setGermanDtoSet(sentence.getGermanSet().stream().map(germanMapper :: mapToDto).collect(Collectors.toSet()));





//       SentenceDto
        return sentenceDto;
    }
}
