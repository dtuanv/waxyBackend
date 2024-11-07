package com.waxy.service.mapper.layout;

import com.waxy.database.entity.EssentialLink;
import com.waxy.database.repository.EssentialLinkRepository;
import com.waxy.dto.EssentialLinkDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class EssentialLinkMapperTest {

    @InjectMocks
    EssentialLinkMapper essentialLinkMapper;

    @Mock
    EssentialLinkRepository essentialLinkRepository;

    @Test
    void shouldIncludeChildrenWhenMappingToDto(){
        EssentialLink restaurantLink = new EssentialLink();

        restaurantLink.setId(1);
        restaurantLink.setTitle("Restaurant ABC");
        restaurantLink.setLink("https://restaurant-abc.com");
        restaurantLink.setDescription("A fine dining restaurant");
        restaurantLink.setCaption("Restaurant");
        restaurantLink.setIcon("add");
        restaurantLink.setIsActive(true);



        EssentialLink restaurantLink2 = new EssentialLink();
        restaurantLink2.setId(2);
        restaurantLink2.setTitle("restaurantLink2 ABC");
        restaurantLink2.setLink("child.com");
        restaurantLink2.setDescription("A fine child ");
        restaurantLink2.setCaption("child");
        restaurantLink2.setIcon("add");
        restaurantLink2.setIsActive(true);
        restaurantLink2.setParent(restaurantLink);


        Mockito.when(essentialLinkRepository.findEssentialLinkByParentId(restaurantLink.getId())).thenReturn(List.of(restaurantLink2)) ;
        EssentialLinkDto essentialLinkDto = essentialLinkMapper.mapToDto(restaurantLink);
        Assertions.assertEquals(essentialLinkDto.getIsActive(), true);
        Assertions.assertEquals(essentialLinkDto.getLink(), "https://restaurant-abc.com");
        Assertions.assertEquals(essentialLinkDto.getChildren().get(0).getLink(), "child.com");
        Assertions.assertEquals(essentialLinkDto.getChildren().get(0).getIsActive(), true);

    }

}