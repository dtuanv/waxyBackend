package com.waxy.service.layout;

import com.waxy.database.entity.EssentialLink;
import com.waxy.database.repository.EssentialLinkGroupRelationRepository;
import com.waxy.database.repository.EssentialLinkGroupRepository;
import com.waxy.database.repository.EssentialLinkRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class EssentialLinkServiceTest {
    @Autowired
    EssentialLinkRepository essentialLinkRepository;

    @Autowired
    EssentialLinkService essentialLinkService;
    @Autowired
    EssentialLinkGroupRepository essentialLinkGroupRepository;

    @Autowired
    EssentialLinkGroupRelationRepository essentialLinkGroupRelationRepository;

    @Test
    void testGetAllActiveLinksWithChildren() {
        EssentialLink restaurantLink = new EssentialLink();
        restaurantLink.setTitle("Restaurant ABC");
        restaurantLink.setLink("https://restaurant-abc.com");
        restaurantLink.setDescription("A fine dining restaurant");
        restaurantLink.setCaption("Restaurant");
        restaurantLink.setIcon("add");
        restaurantLink.setIsActive(true);

        EssentialLink restaurantLinkSaved = essentialLinkRepository.save(restaurantLink);

        EssentialLink restaurantLink2 = new EssentialLink();
        restaurantLink2.setTitle("restaurantLink2 ABC");
        restaurantLink2.setLink("https://restaurantLink2-abc.com");
        restaurantLink2.setDescription("A fine restaurantLink2 restaurant");
        restaurantLink2.setCaption("restaurantLink2");
        restaurantLink2.setIcon("add");
        restaurantLink2.setIsActive(true);
        restaurantLink2.setParent(restaurantLinkSaved);

        essentialLinkRepository.save(restaurantLink2);

        List<EssentialLink> essentialLinkList = essentialLinkService.getAllActiveLinksWithChildren();
        essentialLinkList.stream().forEach(link -> {
            System.out.println(link.getLink());
            System.out.println(link.getChildren().get(0).getLink());

            Assertions.assertEquals(link.getChildren().get(0).getLink().equals("https://restaurantLink2-abc.com"), true);
        });
        System.out.println(essentialLinkList);

        Assertions.assertEquals(essentialLinkList.size() > 0, true);


    }

    // Test configuration for mocking JavaMailSender

}