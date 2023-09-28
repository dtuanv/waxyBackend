package com.waxy.controller;

import com.waxy.database.entity.Vacation;
import com.waxy.database.repository.VacationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class VacationController {

    final VacationRepository vacationRepository;

    @PostMapping("/saveVacation")
    private void saveVacation(@RequestBody Set<Vacation> vacationSet){
      vacationRepository.saveAll(vacationSet);

//        System.out.println("save Vacation");
    }

    @GetMapping("/getVacations/business/{businessId}")
    private Set<Vacation> getVacations(@PathVariable int businessId){
        return vacationRepository.findAllByBusinessId(businessId).stream().collect(Collectors.toSet());
    }

    @GetMapping("/numNotConfirmedVacation/business/{businessId}")
    private int countVacation(@PathVariable int businessId){
        return vacationRepository.countVacationIsNotConfirm(businessId);
    }

    @GetMapping("/getNotConfirmedVacation/business/{businessId}")
    private LinkedHashSet<Vacation> findNotConfirmedVacation(@PathVariable int businessId){
        return vacationRepository.findVacationIsNotConfirm(businessId);
    }

    @GetMapping("/getNotConfirmedVacation/userInfoId/{userInfoId}")
    private LinkedHashSet<Vacation> findNotConfirmedVacationByUserInfoId(@PathVariable int userInfoId){
        return vacationRepository.findVacationIsNotConfirmByUserInfoId(userInfoId);
    }
    @GetMapping("/updateVacation/vacation/{vacationId}/column/{column}")
    private void updateVacation(@PathVariable long vacationId, @PathVariable String column ){
        if(column.equals("is_rejected")){
            vacationRepository.updateVacationColumnIsRejected(vacationId);
        }else {
            vacationRepository.updateVacationColumnIsConfirmed(vacationId);
        }

    }
}
