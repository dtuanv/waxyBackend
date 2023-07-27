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

    @GetMapping("/wishMessageToMe/bUserId/{bUserId}/today/{today}")
    private Set<WishMessage> getWishMessageToMe(@PathVariable long bUserId, @PathVariable String today) {
      return   wishMessageRepository.findWishMessageToMe(today, bUserId);
    }
}
