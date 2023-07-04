package com.waxy.controller;

import com.waxy.database.entity.Agenda;
import com.waxy.database.entity.StaffItem;
import com.waxy.database.entity.Workplan;
import com.waxy.database.repository.WorkplanRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.LinkedHashSet;
import java.util.Set;

@DataJpaTest
class WorkplanControllerTest {

        @Autowired
        private WorkplanRepository workplanRepository;

    @Test
    void saveWorkplan(){
        Set<StaffItem> staffSet = new LinkedHashSet<>();
        StaffItem staff1 = new StaffItem();
        staff1.setTitle("Tuan");
        staff1.setIDay(1);

        StaffItem staff2 = new StaffItem();
        staff2.setTitle("Oanh");
        staff2.setIDay(1);
        staffSet.add(staff1);
        staffSet.add(staff2);


        // given
        Workplan workplan = new Workplan();
        workplan.setWorkweek(27);
        workplan.setNotification("Test");

        Agenda agenda = new Agenda();
        agenda.setMon(staffSet);

        workplan.setAgenda(agenda);

        workplanRepository.save(workplan);

        System.out.println("workplan "+workplan);
    }

}