package com.waxy.controller;

import com.waxy.database.entity.EssentialLink;
import com.waxy.database.entity.EssentialLinkGroup;
import com.waxy.database.entity.EssentialLinkGroupRelation;
import com.waxy.database.repository.EssentialLinkGroupRelationRepository;
import com.waxy.database.repository.EssentialLinkGroupRepository;
import com.waxy.database.repository.EssentialLinkRepository;
import com.waxy.service.layout.EssentialLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    public List<EssentialLink> getAllActiveLinkWithChildren(){
        return essentialLinkService.getAllActiveLinksWithChildren();
    }
}
