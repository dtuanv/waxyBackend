package com.waxy.controller;

import com.waxy.database.entity.layout.EssentialLinkEntity;
import com.waxy.database.repository.layout.EssentialLinkGroupRelationRepository;
import com.waxy.database.repository.layout.EssentialLinkGroupRepository;
import com.waxy.database.repository.layout.EssentialLinkRepository;
import com.waxy.service.layout.EssentialLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/links")
public class LayoutController {

    @Autowired
    private EssentialLinkService essentialLinkService;
    @Autowired
    private EssentialLinkRepository essentialLinkRepository;
    @Autowired
    EssentialLinkGroupRepository essentialLinkGroupRepository;

    @Autowired
    EssentialLinkGroupRelationRepository essentialLinkGroupRelationRepository;


    @GetMapping("/all")
    public List<EssentialLinkEntity> getAllActiveLinkWithChildren(){
        return essentialLinkService.getAllActiveLinksWithChildren();
    }
}
