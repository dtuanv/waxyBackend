package com.waxy.service.mapper;

import com.waxy.database.dto.BetDto;
import com.waxy.database.entity.Bet;
import com.waxy.database.entity.Topic;
import com.waxy.database.repository.BetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleBetMapper extends BetMapper{
    final BetRepository betRepository;
    @Override
    public Bet mapToEntity(BetDto betDto) {
        Bet bet;
        if(betDto.getId() > 0){
            bet = betRepository.findById(betDto.getId()).orElseThrow(() -> new IllegalArgumentException(
                    String.format("Bet can not be found  By ID: "+betDto.getId())
            ));
        }else {
            bet = new Bet();
        }
        bet.setName(betDto.getName());
        bet.setName1(betDto.getName1());

        bet.setName2(betDto.getName2());

        bet.setName3(betDto.getName3());

        bet.setName4(betDto.getName4());
        bet.setName5(betDto.getName5());
        bet.setName6(betDto.getName6());
        bet.setFirstSelected(betDto.getFirstSelected());
        bet.setSecondSelected(betDto.getSecondSelected());
        bet.setThirdSelected(betDto.getThirdSelected());
        bet.setAmount(betDto.getAmount());
        bet.setProfit(betDto.getProfit());



        return bet;
    }

    @Override
    public BetDto mapToDto(Bet bet) {

        BetDto betDto = new BetDto();
        betDto.setId(bet.getId());

        betDto.setName(bet.getName());

        betDto.setName1(bet.getName1());

        betDto.setName2(bet.getName2());

        betDto.setName3(bet.getName3());

        betDto.setName4(bet.getName4());
        betDto.setName5(bet.getName5());
        betDto.setName6(bet.getName6());
        betDto.setFirstSelected(bet.getFirstSelected());
        betDto.setSecondSelected(bet.getSecondSelected());
        betDto.setThirdSelected(bet.getThirdSelected());
        betDto.setAmount(bet.getAmount());
        betDto.setProfit(bet.getProfit());

        return betDto;
    }
}
