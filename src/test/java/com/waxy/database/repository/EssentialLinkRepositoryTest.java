package com.waxy.database.repository;

import com.waxy.database.entity.layout.EssentialLinkEntity;
import com.waxy.database.entity.layout.EssentialLinkGroup;
import com.waxy.database.entity.layout.EssentialLinkGroupRelation;
import com.waxy.database.repository.layout.EssentialLinkGroupRelationRepository;
import com.waxy.database.repository.layout.EssentialLinkGroupRepository;
import com.waxy.database.repository.layout.EssentialLinkRepository;
import com.waxy.dto.layout.EssentialLinkDto;
import com.waxy.service.mapper.layout.EssentialLinkMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class EssentialLinkRepositoryTest {
    @Autowired
    EssentialLinkRepository essentialLinkRepository;


    @Autowired
    EssentialLinkGroupRepository essentialLinkGroupRepository;

    @Autowired
    EssentialLinkGroupRelationRepository essentialLinkGroupRelationRepository;
    EssentialLinkMapper essentialLinkMapper;

    @BeforeEach
    void setUp(){
      essentialLinkMapper = new EssentialLinkMapper();

    }

    @Test
    void testSaveEssentialLinkWithGroup() {
        EssentialLinkGroup essentialLinkGroup = new EssentialLinkGroup();

        essentialLinkGroup.setName("Restaurant");
        essentialLinkGroup.setDescription("For Restaurant");
        essentialLinkGroup =  essentialLinkGroupRepository.save(essentialLinkGroup);



        EssentialLinkEntity restaurantLink = new EssentialLinkEntity();
        restaurantLink.setTitle("Restaurant ABC");
        restaurantLink.setLink("https://restaurant-abc.com");
        restaurantLink.setDescription("A fine dining restaurant");
        restaurantLink.setCaption("Restaurant");
        restaurantLink.setIcon("add");
        restaurantLink.setIsActive(true);

        restaurantLink = essentialLinkRepository.save(restaurantLink);

        EssentialLinkEntity restaurantLink2 = new EssentialLinkEntity();
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
    void testSaveEssentialLink_SavesParentWithChildren(){
        EssentialLinkDto essentialLinkDtoChild = new EssentialLinkDto();
//        essentialLinkDtoChild.setId(11);
        essentialLinkDtoChild.setTitle("Child1");
        EssentialLinkDto essentialLinkDtoChild2 = new EssentialLinkDto();
        essentialLinkDtoChild2.setTitle("Child2");
        EssentialLinkDto essentialLinkDtoParent = new EssentialLinkDto();
//        essentialLinkDtoParent.setId(1);
        essentialLinkDtoParent.setTitle("Parent");
        essentialLinkDtoParent.setLink("Parent");
        essentialLinkDtoParent.getChildren().add(essentialLinkDtoChild);
        essentialLinkDtoParent.getChildren().add(essentialLinkDtoChild2);

        essentialLinkRepository.save(essentialLinkMapper.mapToEntity(essentialLinkDtoParent));

        List<EssentialLinkEntity> essentialLinkEntityList = essentialLinkRepository.findAll();

        Assertions.assertEquals(essentialLinkEntityList.size(), 3);
        Assertions.assertEquals(essentialLinkEntityList.get(0).getTitle(), "Parent");
        Assertions.assertEquals(essentialLinkEntityList.get(0).getChildren().size(), 2);
        Assertions.assertEquals(essentialLinkEntityList.get(0).getChildren().get(0).getTitle(), "Child1");
        Assertions.assertEquals(essentialLinkEntityList.get(0).getChildren().get(1).getTitle(), "Child2");
        Assertions.assertEquals(essentialLinkEntityList.get(1).getParent().getTitle(), "Parent");


    }

    @Test
   void testUpdatingParentAndChildren(){
        EssentialLinkDto essentialLinkDtoChild = new EssentialLinkDto();
        essentialLinkDtoChild.setTitle("Child1");
        EssentialLinkDto essentialLinkDtoChild2 = new EssentialLinkDto();
        essentialLinkDtoChild2.setTitle("Child2");
        EssentialLinkDto essentialLinkDtoParent = new EssentialLinkDto();
        essentialLinkDtoParent.setTitle("Parent");
        essentialLinkDtoParent.setLink("Parent");
        essentialLinkDtoParent.getChildren().add(essentialLinkDtoChild);
        essentialLinkDtoParent.getChildren().add(essentialLinkDtoChild2);

        EssentialLinkEntity savedEssentialLinkEntity = essentialLinkRepository.save(essentialLinkMapper.mapToEntity(essentialLinkDtoParent));


        essentialLinkDtoChild.setTitle("Child1 update");

        essentialLinkDtoChild2.setTitle("Child2 update");

        savedEssentialLinkEntity.getChildren().stream().forEach(c -> {

            if(c.getTitle().equals("Child1")){
                essentialLinkDtoChild.setId(c.getId());
            }
            if(c.getTitle().equals("Child2")){
                essentialLinkDtoChild2.setId(c.getId());
            }
        });
        essentialLinkDtoParent.setTitle("ParentUpdate");
        essentialLinkDtoParent.setId(savedEssentialLinkEntity.getId());

        essentialLinkDtoParent.getChildren().add(new EssentialLinkDto("Child3"));
        essentialLinkRepository.save(essentialLinkMapper.mapToEntity(essentialLinkDtoParent));

      Optional<EssentialLinkEntity> essentialLinkEntityUpdated =   essentialLinkRepository.findById(savedEssentialLinkEntity.getId());
      Optional<EssentialLinkEntity> essentialLinkEntityChild1Updated =   essentialLinkRepository.findById(essentialLinkDtoChild.getId());
      Optional<EssentialLinkEntity> essentialLinkEntityChild2Updated =   essentialLinkRepository.findById(essentialLinkDtoChild2.getId());


      Assertions.assertEquals(essentialLinkEntityUpdated.orElseThrow().getTitle(), essentialLinkDtoParent.getTitle());
      Assertions.assertEquals(essentialLinkEntityChild1Updated.orElseThrow().getTitle(), essentialLinkDtoChild.getTitle());
      Assertions.assertEquals(essentialLinkEntityChild2Updated.orElseThrow().getTitle(), "Child2 update");

      Assertions.assertEquals(essentialLinkEntityUpdated.orElseThrow().getChildren().size(), 3);


    }


    @Test
    void testFindEssentialLinkByParentId(){
        EssentialLinkEntity restaurantLink = new EssentialLinkEntity();

        restaurantLink.setTitle("Restaurant ABC");
        restaurantLink.setLink("https://restaurant-abc.com");
        restaurantLink.setDescription("A fine dining restaurant");
        restaurantLink.setCaption("Restaurant");
        restaurantLink.setIcon("add");
        restaurantLink.setIsActive(true);

        restaurantLink=   essentialLinkRepository.save(restaurantLink);

        EssentialLinkEntity restaurantLink2 = new EssentialLinkEntity();
        restaurantLink2.setTitle("restaurantLink2 ABC");
        restaurantLink2.setLink("child.com");
        restaurantLink2.setDescription("A fine child ");
        restaurantLink2.setCaption("child");
        restaurantLink2.setIcon("add");
        restaurantLink2.setIsActive(true);
        restaurantLink2.setParent(restaurantLink);

        essentialLinkRepository.save(restaurantLink2);
        System.out.println(restaurantLink.getId());
        List<EssentialLinkEntity> essentialLinkByParentId = essentialLinkRepository.findEssentialLinkByParentId(restaurantLink.getId());

        Assertions.assertEquals(essentialLinkByParentId.size() > 0, true);
    }
}