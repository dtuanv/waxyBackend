package com.waxy.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class WishMessage {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long birthUserId;

    private String message;

    private String today;

    private String fromUser;

    private long fromUserId;

    private long businessId;
}