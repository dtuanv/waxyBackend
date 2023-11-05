package com.waxy.service.mapper;

import com.waxy.database.dto.LAnswerOptionDto;
import com.waxy.database.entity.LAnswerOption;
import com.waxy.database.repository.LAnswerOptionRepository;
import com.waxy.database.repository.LQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class SimpleLAnswerOptionMapper extends LAnswerOptionMapper{

  @Autowired
  LAnswerOptionRepository lAnswerOptionRepository;
    @Override
    public LAnswerOption mapToEntity(LAnswerOptionDto lAnswerOptionDto) {
        LAnswerOption lAnswerOption;
            if(lAnswerOptionDto.getId() > 0){
                lAnswerOption = lAnswerOptionRepository.findById(lAnswerOptionDto.getId()).orElseThrow(()
                        -> new IllegalArgumentException(
                        String.format("lAnswerOption can not found by Id: "+lAnswerOptionDto.getId() )
                ));
            }else {
                lAnswerOption = new LAnswerOption();
            }
             lAnswerOption.setVi(lAnswerOptionDto.getVi());

            lAnswerOption.setLabel(lAnswerOptionDto.getLabel());

            lAnswerOption.setVal(lAnswerOptionDto.getVal());


        return lAnswerOption;
    }

    @Override
    public LAnswerOptionDto mapToDto(LAnswerOption lAnswerOption) {
        LAnswerOptionDto lAnswerOptionDto = new LAnswerOptionDto();
        lAnswerOptionDto.setId(lAnswerOption.getId());
        lAnswerOptionDto.setVi(lAnswerOption.getVi());

        lAnswerOptionDto.setLabel(lAnswerOption.getLabel());

        lAnswerOptionDto.setVal(lAnswerOption.getVal());

        return lAnswerOptionDto;
    }
}
