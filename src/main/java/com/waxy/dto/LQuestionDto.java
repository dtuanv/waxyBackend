package com.waxy.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LQuestionDto {

    private long id;
    private int number;

    private String label;

    private String myAnswer;

    private String vi;

    private String ans;


    private List<LAnswerOptionDto> answerOptions;
}
