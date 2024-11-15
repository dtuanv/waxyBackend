package com.waxy.service.layout;

import com.waxy.database.entity.layout.EssentialLinkGroup;
import com.waxy.database.repository.layout.EssentialLinkGroupRepository;
import com.waxy.dto.layout.EssentialLinkGroupDto;
import com.waxy.service.mapper.layout.EssentialLinkGroupMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@Import({EssentialLinkGroupService.class, EssentialLinkGroupMapper.class})
class EssentialLinkGroupServiceTest {

    @Autowired
    private EssentialLinkGroupService essentialLinkGroupService;

    @Autowired
    private EssentialLinkGroupRepository essentialLinkGroupRepository;


    @Test
    void testSaveOrUpdateEssentialLinkGroup(){
        EssentialLinkGroupDto essentialLinkGroupDto = new EssentialLinkGroupDto("waxy", "of waxy");

        essentialLinkGroupService.saveOrUpdate(essentialLinkGroupDto);
        List<EssentialLinkGroup> essentialLinkGroupList = essentialLinkGroupRepository.findAll();

        Assertions.assertEquals(essentialLinkGroupList.get(0).getName(), "waxy");

        Integer savedId = essentialLinkGroupList.get(0).getId();
        essentialLinkGroupDto.setId(savedId);

        essentialLinkGroupDto.setName("waxy update");
        essentialLinkGroupService.saveOrUpdate(essentialLinkGroupDto);

        Optional<EssentialLinkGroup> essentialLinkGroupListUpdate = essentialLinkGroupRepository.findById(savedId);


        Assertions.assertEquals(essentialLinkGroupListUpdate.orElseThrow().getId(), savedId);
        Assertions.assertEquals(essentialLinkGroupListUpdate.orElseThrow().getName(), "waxy update");

    }

}