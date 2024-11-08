package com.waxy.service.mapper.layout;

import com.waxy.database.entity.EssentialLinkEntity;
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


    public EssentialLinkDto mapToDto(EssentialLinkEntity essentialLinkEntity){
        EssentialLinkDto essentialLinkDto = new EssentialLinkDto();

        essentialLinkDto.setId(essentialLinkEntity.getId());
        essentialLinkDto.setLink(essentialLinkEntity.getLink());
        essentialLinkDto.setIcon(essentialLinkEntity.getIcon());
        essentialLinkDto.setCaption(essentialLinkEntity.getCaption());
        essentialLinkDto.setDescription(essentialLinkEntity.getDescription());
        essentialLinkDto.setTitle(essentialLinkEntity.getTitle());
        essentialLinkDto.setIsActive(essentialLinkEntity.getIsActive());

        List<EssentialLinkEntity> essentialLinkList = essentialLinkRepository.findEssentialLinkByParentId(essentialLinkEntity.getId());
        List<EssentialLinkDto> essentialLinkDtoList = essentialLinkList.stream().map(this::mapToDto).collect(Collectors.toList());
        essentialLinkDto.setChildren(essentialLinkDtoList);

        return essentialLinkDto;
    }

    public EssentialLinkEntity mapToEntity(EssentialLinkDto essentialLinkDto){
        EssentialLinkEntity essentialLinkEntity = new EssentialLinkEntity();
        essentialLinkEntity.setId(essentialLinkDto.getId());
        essentialLinkEntity.setLink(essentialLinkDto.getLink());
        essentialLinkEntity.setCaption(essentialLinkDto.getCaption());
        essentialLinkEntity.setDescription(essentialLinkDto.getDescription());
        essentialLinkEntity.setIcon(essentialLinkDto.getIcon());
        essentialLinkEntity.setTitle(essentialLinkDto.getTitle());
        if(essentialLinkDto.getChildren().size() > 0 ){
            for (EssentialLinkDto child : essentialLinkDto.getChildren()) {
                EssentialLinkEntity childEntity = mapToEntity(child);
                childEntity.setParent(essentialLinkEntity);
                essentialLinkEntity.getChildren().add(childEntity);
            }
        }

        return essentialLinkEntity;
    }

}
