package com.waxy.service.mapper;

import com.waxy.database.entity.German;
import com.waxy.database.repository.GermanRepository;
import com.waxy.dto.GermanDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleGermanMapper extends GermanMapper{
    final GermanRepository germanRepository;
    @Override
    public German mapToEntity(GermanDto germanDto) {
        German german;
        if(germanDto.getId() > 0){
            german = germanRepository.findById(germanDto.getId())
                    .orElseThrow(() -> new IllegalArgumentException
                            (String.format("Can not find German ID: "+germanDto.getId())));
        }else {
            german = new German();

        }
        german.setGerSentence(germanDto.getGerSentence());

//        german.setSentence(sentenceMapper.mapToEntity(germanDto.getSentenceDto()));

        return german;
    }

    @Override
    public GermanDto mapToDto(German german) {

        GermanDto germanDto = new GermanDto();

        germanDto.setId(german.getId());

        germanDto.setGerSentence(german.getGerSentence());

        return germanDto;
    }
}
