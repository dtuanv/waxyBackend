package com.waxy.database.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import java.time.LocalDate;

@MappedSuperclass
@Setter
@Getter
public class DataObject {
    LocalDate updateAt;

    DataObject(){
        updateAt = LocalDate.now();
    }

    @PreUpdate
    public void onUpdate(){
        updateAt = LocalDate.now();
    }
}


