package com.waxy.service.mapper;

import com.waxy.database.dto.LQuestionDto;
import com.waxy.database.entity.LQuestion;
import com.waxy.database.repository.LQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SimpleLQuestionMapper extends LQuestionMapper{
    @Autowired
    LQuestionRepository lQuestionRepository;
    @Autowired
    protected  LAnswerOptionMapper lAnswerOptionMapper;
    @Override
    public LQuestion mapToEntity(LQuestionDto lQuestionDto) {
        LQuestion lQuestion;
        if(lQuestionDto.getId() > 0){
            lQuestion =  lQuestionRepository.findById(lQuestionDto.getId()).orElseThrow(() -> new IllegalArgumentException(
                    String.format("lQuestion can not be found by ID: ", lQuestionDto.getId())
            ));
        }else {
            lQuestion = new LQuestion();
        }
        lQuestion.setVi(lQuestionDto.getVi());
        lQuestion.setNumber(lQuestionDto.getNumber());
        lQuestion.setLabel(lQuestionDto.getLabel());
        lQuestion.setMyAnswer(lQuestionDto.getMyAnswer());

        lQuestion.setAnswerOptions(lQuestionDto.getAnswerOptions().stream()
                .map(lAnswerOptionMapper :: mapToEntity).collect(Collectors.toList()));

        lQuestion.setAns(lQuestionDto.getAns());
        return lQuestion;
    }

    @Override
    public LQuestionDto mapToDto(LQuestion lQuestion) {
        LQuestionDto lQuestionDto = new LQuestionDto();

        lQuestionDto.setId(lQuestion.getId());

        lQuestionDto.setVi(lQuestion.getVi());

        lQuestionDto.setNumber(lQuestion.getNumber());

        lQuestionDto.setLabel(lQuestion.getLabel());

        lQuestionDto.setMyAnswer(lQuestion.getMyAnswer());

        lQuestionDto.setAnswerOptions(lQuestion.getAnswerOptions().stream()
                .map(lAnswerOptionMapper :: mapToDto).collect(Collectors.toList()));

        lQuestionDto.setAns(lQuestion.getAns());

        return lQuestionDto;
    }
}
