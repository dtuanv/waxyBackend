package com.waxy.database.repository;

import com.waxy.database.entity.EssentialLink;
import com.waxy.database.entity.EssentialLinkGroup;
import com.waxy.database.entity.EssentialLinkGroupRelation;
import com.waxy.service.layout.EssentialLinkService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")
class EssentialLinkRepositoryTest {
    @Autowired
    EssentialLinkRepository essentialLinkRepository;


    @Autowired
    EssentialLinkGroupRepository essentialLinkGroupRepository;

    @Autowired
    EssentialLinkGroupRelationRepository essentialLinkGroupRelationRepository;

    @Test
    void testSaveEssentialLink() {
        EssentialLinkGroup essentialLinkGroup = new EssentialLinkGroup();

        essentialLinkGroup.setName("Restaurant");
        essentialLinkGroup.setDescription("For Restaurant");
        essentialLinkGroup =  essentialLinkGroupRepository.save(essentialLinkGroup);



        EssentialLink restaurantLink = new EssentialLink();
        restaurantLink.setTitle("Restaurant ABC");
        restaurantLink.setLink("https://restaurant-abc.com");
        restaurantLink.setDescription("A fine dining restaurant");
        restaurantLink.setCaption("Restaurant");
        restaurantLink.setIcon("add");
        restaurantLink.setIsActive(true);

        restaurantLink = essentialLinkRepository.save(restaurantLink);

        EssentialLink restaurantLink2 = new EssentialLink();
        restaurantLink2.setTitle("restaurantLink2 ABC");
        restaurantLink2.setLink("https://restaurantLink2-abc.com");
        restaurantLink2.setDescription("A fine restaurantLink2 restaurant");
        restaurantLink2.setCaption("restaurantLink2");
        restaurantLink2.setIcon("add");
        restaurantLink2.setIsActive(true);
        restaurantLink2.setParent(restaurantLink);

        essentialLinkRepository.save(restaurantLink2);

        List<EssentialLinkGroupRelation> essentialLinkGroupRelationList = new ArrayList<>();
        EssentialLinkGroupRelation essentialLinkGroupRelation = new EssentialLinkGroupRelation();

        essentialLinkGroupRelation.setEssentialLinkGroup(essentialLinkGroup);
        essentialLinkGroupRelation.setEssentialLink(restaurantLink);

        EssentialLinkGroupRelation essentialLinkGroupRelation2 = new EssentialLinkGroupRelation();

        essentialLinkGroupRelation2.setEssentialLinkGroup(essentialLinkGroup);
        essentialLinkGroupRelation2.setEssentialLink(restaurantLink2);

        essentialLinkGroupRelationList.add(essentialLinkGroupRelation);
        essentialLinkGroupRelationList.add(essentialLinkGroupRelation2);

        essentialLinkGroupRelationRepository.saveAll(essentialLinkGroupRelationList);

        List<EssentialLinkGroupRelation> resultList = essentialLinkGroupRelationRepository.findByGroupId(essentialLinkGroup.getId());

        Assertions.assertEquals(resultList.size(), 2);
        Assertions.assertEquals(resultList.get(0).getEssentialLinkGroup().getId() > 0, true);

    }

    @Test
    void testFindEssentialLinkByParentId(){
        EssentialLink restaurantLink = new EssentialLink();

        restaurantLink.setTitle("Restaurant ABC");
        restaurantLink.setLink("https://restaurant-abc.com");
        restaurantLink.setDescription("A fine dining restaurant");
        restaurantLink.setCaption("Restaurant");
        restaurantLink.setIcon("add");
        restaurantLink.setIsActive(true);

        restaurantLink=   essentialLinkRepository.save(restaurantLink);

        EssentialLink restaurantLink2 = new EssentialLink();
        restaurantLink2.setTitle("restaurantLink2 ABC");
        restaurantLink2.setLink("child.com");
        restaurantLink2.setDescription("A fine child ");
        restaurantLink2.setCaption("child");
        restaurantLink2.setIcon("add");
        restaurantLink2.setIsActive(true);
        restaurantLink2.setParent(restaurantLink);

        essentialLinkRepository.save(restaurantLink2);

        List<EssentialLink> essentialLinkByParentId = essentialLinkRepository.findEssentialLinkByParentId(restaurantLink.getId());

        Assertions.assertEquals(essentialLinkByParentId.size() > 0, true);
    }
}