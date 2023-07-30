package com.waxy.controller;

import com.waxy.database.entity.WishMessage;
import com.waxy.database.repository.WishMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class WishMessageController {
    private final WishMessageRepository wishMessageRepository;

    @PostMapping("/saveWishMessage")
    private void saveWishMessage(@RequestBody WishMessage wishMessage){
              wishMessageRepository.save(wishMessage);
    }

    @GetMapping("/wishMessage/bUserId/{bUserId}")
    private Set<WishMessage> getWishMessageByBUserId(@PathVariable long bUserId){
        return wishMessageRepository.findWishMessageByBUserId(bUserId);
    }

    @GetMapping("/wishMessageToMe/bUserId/{bUserId}/day/{day}/month/{month}")
    private Set<WishMessage> getWishMessageToMe(@PathVariable long bUserId,@PathVariable int day, @PathVariable int month) {

        System.out.println("month "+month);
        System.out.println("day "+day);
        return   wishMessageRepository.findWishMessageToMe(month, day, bUserId);
    }
}
