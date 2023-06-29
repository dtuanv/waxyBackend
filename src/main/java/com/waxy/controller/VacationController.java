package com.waxy.controller;

import com.waxy.database.entity.Vacation;
import com.waxy.database.repository.VacationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
