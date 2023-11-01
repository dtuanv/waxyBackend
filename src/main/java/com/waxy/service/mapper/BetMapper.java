package com.waxy.service.mapper;

import com.waxy.database.dto.BetDto;
import com.waxy.database.entity.Bet;

public abstract class BetMapper {
    public abstract Bet mapToEntity(BetDto betDto);
    public abstract BetDto mapToDto(Bet bet);
}
