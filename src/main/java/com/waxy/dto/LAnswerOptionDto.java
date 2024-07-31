package com.waxy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LAnswerOptionDto {

    private long id;

    private String label;

    private String val;

    private String vi;

    private LQuestionDto question;
}
