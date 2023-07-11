package com.waxy.service.mapper;

import com.waxy.database.dto.UserInfoDto;
import com.waxy.database.entity.UserInfo;

public abstract class UserInfoMapper {
    public abstract UserInfoDto mapToDto(UserInfo userInfo);

    public abstract UserInfo mapToEntity(UserInfoDto userInfoDto);
}
