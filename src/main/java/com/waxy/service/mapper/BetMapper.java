package com.waxy.service.mapper;

import com.waxy.database.entity.Bet;
import com.waxy.dto.BetDto;

public abstract class BetMapper {
    public abstract Bet mapToEntity(BetDto betDto);
    public abstract BetDto mapToDto(Bet bet);
}
