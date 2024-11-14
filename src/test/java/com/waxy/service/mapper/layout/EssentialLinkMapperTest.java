package com.waxy.service.mapper.layout;

import com.waxy.database.entity.layout.EssentialLinkEntity;
import com.waxy.database.repository.layout.EssentialLinkRepository;
import com.waxy.dto.layout.EssentialLinkDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EssentialLinkMapperTest {

    @InjectMocks
    EssentialLinkMapper essentialLinkMapper;

    @Mock
    EssentialLinkRepository essentialLinkRepository;

    @Test
    void shouldIncludeChildrenWhenMappingToDto(){
        EssentialLinkEntity restaurantLink = new EssentialLinkEntity();

        restaurantLink.setId(1);
        restaurantLink.setTitle("Restaurant ABC");
        restaurantLink.setLink("https://restaurant-abc.com");
        restaurantLink.setDescription("A fine dining restaurant");
        restaurantLink.setCaption("Restaurant");
        restaurantLink.setIcon("add");
        restaurantLink.setIsActive(true);
        EssentialLinkEntity restaurantLink2 = new EssentialLinkEntity();
        restaurantLink2.setId(2);
        restaurantLink2.setTitle("restaurantLink2 ABC");
        restaurantLink2.setLink("child.com");
        restaurantLink2.setDescription("A fine child ");
        restaurantLink2.setCaption("child");
        restaurantLink2.setIcon("add");
        restaurantLink2.setIsActive(true);
        restaurantLink2.setParent(restaurantLink);

        restaurantLink.getChildren().add(restaurantLink2);


//        Mockito.when(essentialLinkRepository.findEssentialLinkByParentId(restaurantLink.getId())).thenReturn(List.of(restaurantLink2)) ;
        EssentialLinkDto essentialLinkDto = essentialLinkMapper.mapToDto(restaurantLink);
        Assertions.assertEquals(essentialLinkDto.getIsActive(), true);
        Assertions.assertEquals(essentialLinkDto.getLink(), "https://restaurant-abc.com");
        Assertions.assertEquals(essentialLinkDto.getChildren().get(0).getLink(), "child.com");
        Assertions.assertEquals(essentialLinkDto.getChildren().get(0).getIsActive(), true);

    }

    @Test
    void testEssentialLinKDtoMapToEntity(){

        EssentialLinkDto essentialLinkDtoChild = new EssentialLinkDto();
        essentialLinkDtoChild.setId(11);
        essentialLinkDtoChild.setTitle("Child1");
        EssentialLinkDto essentialLinkDtoChild2 = new EssentialLinkDto();
        essentialLinkDtoChild2.setTitle("Child2");
        EssentialLinkDto essentialLinkDtoParent = new EssentialLinkDto();
        essentialLinkDtoParent.setId(1);
        essentialLinkDtoParent.setTitle("Parent");
        essentialLinkDtoParent.setLink("Parent");
        essentialLinkDtoParent.getChildren().add(essentialLinkDtoChild);
        essentialLinkDtoParent.getChildren().add(essentialLinkDtoChild2);
        EssentialLinkEntity essentialLinkEntity = essentialLinkMapper.mapToEntity(essentialLinkDtoParent);
        Assertions.assertEquals(essentialLinkEntity.getId(), 1);
        Assertions.assertEquals(essentialLinkEntity.getChildren().size(), 2);
        Assertions.assertEquals(essentialLinkEntity.getChildren().get(0).getParent().getTitle(), "Parent");
        Assertions.assertEquals(essentialLinkEntity.getChildren().get(0).getTitle(), "Child1");
        Assertions.assertEquals(essentialLinkEntity.getChildren().get(0).getId(), 11);
        Assertions.assertEquals(essentialLinkEntity.getChildren().get(1).getTitle(), "Child2");
    }


}