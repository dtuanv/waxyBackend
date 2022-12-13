package com.waxy.controller;

import com.waxy.database.entity.Notice;
import com.waxy.database.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NoticeController {
    @Autowired
    private NoticeRepository noticeRepository;
    @GetMapping("/admin/getNotice")
    private Notice getNotice(){
        return noticeRepository.findANotice("Home Page");
    }
    @GetMapping("/admin/getProductNotice")
    private Notice getProductNotice(){
        return noticeRepository.findANotice("Product Page");
    }
    @GetMapping("/getNotice/homePage")
    private Notice getNoticeHomePageForAll(){
        return noticeRepository.findANotice("Home Page");
    }
    @GetMapping("/getNotice/productPage")
    private Notice getNoticeProductPageForAll(){
        return noticeRepository.findANotice("Product Page");
    }

    @PostMapping("/admin/notice/save")
    private void saveNotice(@RequestBody Notice notice){
        noticeRepository.save(notice);
    };
    @PutMapping("/admin/notice/edit/{id}")
    private ResponseEntity<Notice> updateNotice(@PathVariable int id, @RequestBody Notice noticeDetails){
        Notice updateNotice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not exist with id: " + id));
        updateNotice.setDescription(noticeDetails.getDescription());
        updateNotice.setStatus(noticeDetails.getStatus());
        noticeRepository.save(updateNotice);
        return ResponseEntity.ok(updateNotice);
    }

}
