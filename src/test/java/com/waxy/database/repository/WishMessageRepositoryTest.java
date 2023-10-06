package com.waxy.database.repository;

import com.waxy.database.entity.WishMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class WishMessageRepositoryTest {

    @Autowired
    WishMessageRepository wishMessageRepository;

    @Test
    public void checkWishMessageFromUserTest(){
        WishMessage wishMessage = WishMessage.builder().birthUserId(2).fromUser("from").fromUserId(1)
                .createAt("06.10.2023").message("cmsn").build();
        wishMessageRepository.save(wishMessage);

        Set<WishMessage> checkWishMessage = wishMessageRepository.checkWishMessageFromUser(wishMessage.getFromUserId()
                , wishMessage.getBirthUserId(),wishMessage.getCreateAt()).stream().collect(Collectors.toSet());

        Assertions.assertThat(checkWishMessage.size()).isGreaterThan(0);
    }

}