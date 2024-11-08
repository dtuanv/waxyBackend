package com.waxy.service.layout;

import com.waxy.database.entity.EssentialLinkEntity;
import com.waxy.database.repository.EssentialLinkGroupRelationRepository;
import com.waxy.database.repository.EssentialLinkGroupRepository;
import com.waxy.database.repository.EssentialLinkRepository;
import com.waxy.dto.EssentialLinkDto;
import com.waxy.service.mapper.layout.EssentialLinkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EssentialLinkService {

    @Autowired
    EssentialLinkRepository essentialLinkRepository;

    @Autowired
    EssentialLinkMapper essentialLinkMapper;

    @Autowired
    EssentialLinkGroupRepository essentialLinkGroupRepository;

    @Autowired
    EssentialLinkGroupRelationRepository essentialLinkGroupRelationRepository;



    public List<EssentialLinkEntity> getAllActiveLinksWithChildren(){
        return essentialLinkRepository.findAllActiveLinksWithChildren();
    }

    public EssentialLinkEntity saveEssentialLink(EssentialLinkDto essentialLinkDto){
       return      essentialLinkRepository.save(essentialLinkMapper.mapToEntity(essentialLinkDto));
    }

    public void addEssentialLinkToEssentialLinkGroup(EssentialLinkDto essentialLinkDto){


    }
}
