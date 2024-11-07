package com.waxy.service.layout;

import com.waxy.database.entity.EssentialLink;
import com.waxy.database.repository.EssentialLinkGroupRelationRepository;
import com.waxy.database.repository.EssentialLinkGroupRepository;
import com.waxy.database.repository.EssentialLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EssentialLinkService {

    @Autowired
    EssentialLinkRepository essentialLinkRepository;

    @Autowired
    EssentialLinkGroupRepository essentialLinkGroupRepository;

    @Autowired
    EssentialLinkGroupRelationRepository essentialLinkGroupRelationRepository;



    public List<EssentialLink> getAllActiveLinksWithChildren(){
        return essentialLinkRepository.findAllActiveLinksWithChildren();
    }
}
