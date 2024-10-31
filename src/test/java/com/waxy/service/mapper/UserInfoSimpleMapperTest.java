package com.waxy.service.mapper;

import com.waxy.database.entity.Business;
import com.waxy.database.entity.UserInfo;
import com.waxy.database.repository.BusinessRepository;
import com.waxy.dto.UserInfoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserInfoSimpleMapperTest {

        @Mock
        private BusinessRepository businessRepository;

        @InjectMocks
        private UserInfoSimpleMapper userInfoSimpleMapper;

        @BeforeEach
        public void setUp() {
                MockitoAnnotations.openMocks(this);
        }
        @Test
        public void mapToDto_shouldMapUserInfoToUserInfoDto(){
                UserInfo userInfoEntity = new UserInfo();
                userInfoEntity.setId(1L);
                userInfoEntity.setUserId(100L);
                userInfoEntity.setRole("ADMIN");
                userInfoEntity.setBusinessId(2L);
                userInfoEntity.setName("John Doe");
                userInfoEntity.setRestVacation(10);
                userInfoEntity.setDepartment("Bar");
                userInfoEntity.setBirthday("1999-01-01");
                userInfoEntity.setPosition("Management");
                userInfoEntity.setLanguage("English");
                userInfoEntity.setFirstLogin(true);

                Business business = new Business(2L, "Name", "waxy", "DE", "On", 5, "Restaurant");
                Mockito.when(businessRepository.findById(2L)).thenReturn(Optional.of(business));

                UserInfoDto userInfoDto = userInfoSimpleMapper.mapToDto(userInfoEntity);

                assertEquals(userInfoDto.getId(), 1);
                assertEquals(userInfoDto.getUserId(), 100);
                assertEquals(userInfoDto.getRole(), "ADMIN");
                assertEquals(userInfoDto.getBusinessId(), 2);

                assertEquals(userInfoDto.getName(), "John Doe");
                assertEquals(userInfoDto.getRestVacation(), 10);
                assertEquals(userInfoDto.getDepartment(), "Bar");
                assertEquals(userInfoDto.getBirthday(), "1999-01-01");
                assertEquals(userInfoDto.getPosition(), "Management");
                assertEquals(userInfoDto.getLanguage(), "English");
                assertEquals(userInfoDto.isFirstLogin(), true);

        }

}