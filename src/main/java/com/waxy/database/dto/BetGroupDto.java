package com.waxy.database.dto;

import com.waxy.database.entity.Bet;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
public class BetGroupDto {

    private long id;
    String name;

    List<BetDto> betList;
}
