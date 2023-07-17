package com.waxy.controller;

import com.waxy.database.entity.NotWorkable;
import com.waxy.database.entity.Vacation;
import com.waxy.database.repository.NotWorkableRepository;
import com.waxy.database.repository.VacationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class NotWorkableController {
    private final NotWorkableRepository notWorkableRepository;
    private final VacationRepository vacationRepository;

    @PostMapping("/saveNotWorkableList")
    private void saveNotWorkable(@RequestBody Set<NotWorkable> notWorkableSet){
        notWorkableRepository.saveAll(notWorkableSet);
    }

    @GetMapping("/getNotWorkable/business/{businessId}")
    private List<NotWorkable> getNotWorkableByBusiness(@PathVariable long businessId){
      return  notWorkableRepository.findByBusinessId(businessId);
    }

    @GetMapping("/getNotWorkableMonthsBecauseOfSchool/business/{businessId}/start/{start}/end/{end}")
    private Set<NotWorkable> getNotWorkableMonthsBecauseOfSchool(@PathVariable long businessId,@PathVariable String start , @PathVariable String end){
        return notWorkableRepository.findNotWorkableMonthsBecauseOfSchool(businessId, start, end);
    }

    @GetMapping("/getNotWorkableBecauseOfVacation/business/{businessId}/start/{start}")
    private Set<Vacation> getNotWorkableBecauseOfVacation(@PathVariable long businessId, @PathVariable String start){
        return vacationRepository.findNotWorkableBecauseOfVacation(businessId, start);
    }
}
