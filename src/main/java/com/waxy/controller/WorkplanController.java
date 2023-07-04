package com.waxy.controller;

import com.waxy.database.entity.Workplan;
import com.waxy.database.repository.WorkplanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WorkplanController {
    final WorkplanRepository workplanRepository;

    @PostMapping("/saveWorkplan")
    private void saveWorkplan(@RequestBody Workplan workplan){

        workplanRepository.save(workplan);
    }


}
