package com.waxy.service.mapper;

import com.waxy.database.dto.UserInfoDto;
import com.waxy.database.entity.Business;
import com.waxy.database.entity.UserInfo;
import com.waxy.database.repository.BusinessRepository;
import com.waxy.database.repository.UserInfoRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoSimpleMapper extends UserInfoMapper{
    final BusinessRepository businessRepository;
    @Override
    public UserInfoDto mapToDto(UserInfo userInfo) {
        UserInfoDto dto = new UserInfoDto();
        dto.setId(userInfo.getId());

        dto.setAvatar(userInfo.getAvatar());

        dto.setUserId(userInfo.getUserId());

        dto.setRole(userInfo.getRole());

        dto.setBusinessId(userInfo.getBusinessId());

        dto.setName(userInfo.getName());

        dto.setRestVacation(userInfo.getRestVacation());
        Business business = businessRepository.findById(Long.valueOf(userInfo.getBusinessId())).orElseThrow(() ->
                new IllegalArgumentException(String.format("Can not found Business By Id "+userInfo.getBusinessId())));
        dto.setBusinessArea(business.getBusinessArea());

        dto.setDepartment(userInfo.getDepartment());

        dto.setBirthday(userInfo.getBirthday());

        dto.setPosition(userInfo.getPosition());

        dto.setLanguage(userInfo.getLanguage());

        dto.setBusinessName(business.getName());

        return dto;
    }

    @Override
    public UserInfo mapToEntity(UserInfoDto userInfoDto) {
        return null;
    }
}
