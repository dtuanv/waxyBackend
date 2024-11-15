package com.waxy.service.layout;

import com.waxy.database.repository.layout.EssentialLinkGroupRepository;
import com.waxy.dto.layout.EssentialLinkGroupDto;
import com.waxy.service.mapper.layout.EssentialLinkGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EssentialLinkGroupService {

    @Autowired
    EssentialLinkGroupRepository essentialLinkGroupRepository;

    @Autowired
    EssentialLinkGroupMapper essentialLinkGroupMapper;

    public void saveOrUpdate(EssentialLinkGroupDto essentialLinkGroupDto){
        essentialLinkGroupRepository.save(essentialLinkGroupMapper.mapToEntity(essentialLinkGroupDto));
    }
    public List<EssentialLinkGroupDto> getAllEssentialLinKGroup(){
      return  essentialLinkGroupRepository.findAll().stream().map(essentialLinkGroupMapper::mapToDto).collect(Collectors.toList());
    }
}
