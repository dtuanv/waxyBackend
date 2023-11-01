package com.waxy.controller;

import com.waxy.database.dto.BetDto;
import com.waxy.database.dto.BetGroupDto;
import com.waxy.database.entity.Bet;
import com.waxy.database.entity.BetGroup;
import com.waxy.database.repository.BetGroupRepository;
import com.waxy.service.mapper.BetGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BetGroupController {
  final BetGroupRepository betGroupRepository;
  final BetGroupMapper betGroupMapper;

  @PostMapping("/saveBetGroup")
    public void saveBetGroup(@RequestBody BetGroupDto betGroupDto){
        BetGroup betGroup = betGroupMapper.mapToEntity(betGroupDto);
        for ( Bet bet: betGroup.getBetList() ) {
            bet.setBetGroup(betGroup);
        }
          betGroupRepository.save(betGroup)  ;
    }

    @GetMapping("/getBetGroup")
    public List<BetGroupDto> getBetGroup(){

      return betGroupRepository.findAll().stream().map(betGroupMapper :: mapToDto).collect(Collectors.toList());
    }

    @GetMapping("/getBetGroup/{betGroupId}")
    public BetGroupDto getBetGroupById(@PathVariable long betGroupId){
        BetGroup betGroup = betGroupRepository.findById(betGroupId).orElseThrow();
        return betGroupMapper.mapToDto(betGroup);
    }
}
