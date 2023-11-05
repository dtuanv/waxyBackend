package com.waxy.controller.lebenindeutschland;

import com.waxy.database.dto.LQuestionDto;
import com.waxy.database.entity.LAnswerOption;
import com.waxy.database.entity.LQuestion;
import com.waxy.database.repository.LQuestionRepository;
import com.waxy.service.mapper.LQuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class LQuestionController {
   final LQuestionRepository lQuestionRepository;
   final LQuestionMapper lQuestionMapper;



    @PostMapping("/lebenindeutschland/saveQuestion")
    private void saveLQuestion(@RequestBody LQuestionDto lQuestionDto){
        LQuestion lQuestion = lQuestionMapper.mapToEntity(lQuestionDto);
        for ( LAnswerOption lAnswerOption : lQuestion.getAnswerOptions()) {
            lAnswerOption.setQuestion(lQuestion);
        }
        lQuestionRepository.save(lQuestion);
    }

    @GetMapping("/lebenindeutschland/allQuestion")
    private List<LQuestionDto> getAllQuestion(){
        return lQuestionRepository.findAll().stream().map(lQuestionMapper:: mapToDto).collect(Collectors.toList());
    }
    @DeleteMapping("/lebenindeutschland/delete/question/{questionId}")
    private void deleteQuestion(@PathVariable long questionId){
        lQuestionRepository.deleteById(questionId);
    }
}
