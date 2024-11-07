package com.waxy.service.mapper.layout;

import com.waxy.database.entity.EssentialLink;
import com.waxy.database.repository.EssentialLinkRepository;
import com.waxy.dto.EssentialLinkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EssentialLinkMapper {

    @Autowired
    EssentialLinkRepository essentialLinkRepository;


    public EssentialLinkDto mapToDto(EssentialLink essentialLink){
        EssentialLinkDto essentialLinkDto = new EssentialLinkDto();

        essentialLinkDto.setId(essentialLink.getId());
        essentialLinkDto.setLink(essentialLink.getLink());
        essentialLinkDto.setIcon(essentialLink.getIcon());
        essentialLinkDto.setCaption(essentialLink.getCaption());
        essentialLinkDto.setDescription(essentialLink.getDescription());
        essentialLinkDto.setTitle(essentialLink.getTitle());
        essentialLinkDto.setIsActive(essentialLink.getIsActive());

        List<EssentialLink> essentialLinkList = essentialLinkRepository.findEssentialLinkByParentId(essentialLink.getId());
        List<EssentialLinkDto> essentialLinkDtoList = essentialLinkList.stream().map(this::mapToDto).collect(Collectors.toList());
        essentialLinkDto.setChildren(essentialLinkDtoList);

        return essentialLinkDto;
    }

}
