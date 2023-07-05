package com.waxy.service;

import com.waxy.database.entity.Agenda;
import com.waxy.database.entity.Workplan;
import org.springframework.stereotype.Service;

@Service
public class WorkplanSaveService {
    public void saveWorkplan(Workplan workplan){
        Workplan workplanToSave = new Workplan();

        workplanToSave.setNotification(workplan.getNotification());

        workplanToSave.setWorkweek(workplan.getWorkweek());

        workplanToSave.setBusinessId(workplan.getBusinessId());
        Agenda agenda = new Agenda();



    }
}
