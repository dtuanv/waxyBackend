package com.waxy.database.dto;

import com.waxy.database.entity.LQuestion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ManyToOne;

@Getter
@Setter
public class LAnswerOptionDto {

    private long id;

    private String label;

    private String val;

    private String vi;

    private LQuestionDto question;
}
