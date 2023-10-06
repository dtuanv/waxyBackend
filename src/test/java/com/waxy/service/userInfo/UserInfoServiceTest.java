package com.waxy.service.userInfo;

import com.waxy.database.entity.UserInfo;
import com.waxy.database.entity.WishMessage;
import com.waxy.database.repository.UserInfoRepository;
import com.waxy.database.repository.WishMessageRepository;
import com.waxy.service.user.UserInfoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//@DataJpaTest
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ExtendWith(MockitoExtension.class)
class UserInfoServiceTest {
    @Mock
    WishMessageRepository wishMessageRepository;

    @Mock
    UserInfoRepository userInfoRepository;

    @InjectMocks
    UserInfoService userInfoService;

    @Test
    public void checkIfUserHasSentMessageThenReturnTrue(){
        WishMessage wishMessage = WishMessage.builder().id(1).birthUserId(2L).fromUser("from").fromUserId(1)
                .createAt("06.10.2023").message("cmsn").build();
        Set<WishMessage> wishMessageSet = new HashSet<>();

        wishMessageSet.add(wishMessage);

        Set<UserInfo> userInfoHasBirthday = new HashSet<>();

        UserInfo userHasBeenSentMessage = new UserInfo();
        userHasBeenSentMessage.setBusinessId(1L);
        userHasBeenSentMessage.setId(1L);

        UserInfo userInfo2 = new UserInfo();
        userInfo2.setBusinessId(2L);
        userInfo2.setId(2);

        userInfoHasBirthday.add(userHasBeenSentMessage);
        userInfoHasBirthday.add(userInfo2);



        when(wishMessageRepository.checkWishMessageFromUser
                (1L,2L,"06.10.2023")).thenReturn(wishMessageSet);




      Set<UserInfo>  userInfoSet = userInfoHasBirthday.stream().filter(userInfo ->
              userInfoService.returnOnlyUserHasNotSentMessage(userInfo.getId(),2L,"06.10.2023")).collect(Collectors.toSet());

        Boolean isExist = userInfoService.returnOnlyUserHasNotSentMessage(1L, wishMessage.getBirthUserId(),wishMessage.getCreateAt());

        Assertions.assertThat(userInfoSet.size()).isLessThan(userInfoHasBirthday.size());

        Assertions.assertThat(isExist).isFalse();
    }

}