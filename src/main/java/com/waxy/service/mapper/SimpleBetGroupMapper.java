package com.waxy.service.mapper;

import com.waxy.database.entity.BetGroup;
import com.waxy.database.repository.BetGroupRepository;
import com.waxy.dto.BetGroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SimpleBetGroupMapper extends BetGroupMapper{
    final BetMapper betMapper;
    final BetGroupRepository betGroupRepository;
    @Override
    public BetGroup mapToEntity(BetGroupDto betGroupDto) {
        BetGroup betGroup;
        if(betGroupDto.getId() > 0){
            betGroup = betGroupRepository.getById(betGroupDto.getId());
        }else{
            betGroup = new BetGroup();
        }
        betGroup.setName(betGroupDto.getName());

        betGroup.setBetList(betGroupDto.getBetList().stream().map(betMapper :: mapToEntity).collect(Collectors.toList()));

        return betGroup;
    }

    @Override
    public BetGroupDto mapToDto(BetGroup betGroup) {

        BetGroupDto betGroupDto = new BetGroupDto();

        betGroupDto.setId(betGroup.getId());
        betGroupDto.setName(betGroup.getName());
        betGroupDto.setBetList(betGroup.getBetList().stream().map(betMapper :: mapToDto).collect(Collectors.toList()));
        return betGroupDto;
    }
}
