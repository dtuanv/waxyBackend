package com.waxy.service.mapper.layout;

import com.waxy.database.entity.layout.EssentialLinkEntity;
import com.waxy.database.entity.layout.EssentialLinkGroup;
import com.waxy.database.repository.layout.EssentialLinkRepository;
import com.waxy.dto.layout.EssentialLinkGroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EssentialLinkGroupMapper {

//    @Autowired
//    EssentialLinkRepository essentialLinkRepository;

    public EssentialLinkGroup mapToEntity(EssentialLinkGroupDto essentialLinkGroupDto){
        EssentialLinkGroup essentialLinkGroup = new EssentialLinkGroup();

        if(essentialLinkGroupDto.getId() != null && essentialLinkGroupDto.getId() > 0){
            essentialLinkGroup.setId(essentialLinkGroupDto.getId());
        }
        essentialLinkGroup.setDescription(essentialLinkGroupDto.getDescription());
        essentialLinkGroup.setName(essentialLinkGroupDto.getName());
        return essentialLinkGroup;
    }

    public EssentialLinkGroupDto mapToDto(EssentialLinkGroup essentialLinkGroup){
        EssentialLinkGroupDto essentialLinkGroupDto = new EssentialLinkGroupDto(essentialLinkGroup.getName(),
                essentialLinkGroup.getDescription());

//        essentialLinkGroupDto.setEssentialLinkDtoList();
        return essentialLinkGroupDto;
    }
}
