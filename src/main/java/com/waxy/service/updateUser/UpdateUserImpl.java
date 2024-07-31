package com.waxy.service.updateUser;

import com.waxy.dto.UserDTO;
import com.waxy.database.entity.UserInfo;
import com.waxy.database.repository.RegisterRepository;
import com.waxy.database.repository.UserInfoRepository;
import com.waxy.security.response.UpdateUserResponse;
import com.waxy.utils.UpdateUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateUserImpl implements UpdateUserService{
    private final RegisterRepository registerRepository;

    private final PasswordEncoder passwordEncoder;
    private final UserInfoRepository userInfoRepository;
    @Override
    public UpdateUserResponse adminDoUpdateUser(UpdateUser updateUser) {
        UpdateUserResponse updateUserResponse = new UpdateUserResponse();
        UpdateUserResponse updateUserResponseErr = new UpdateUserResponse();
        updateUserResponseErr.setMessage("Error! Please log in again.!");
        updateUserResponseErr.setUpdated(false);
        UserInfo bAdmin = userInfoRepository.findById(updateUser.getBAdminId()).orElseThrow(() -> new IllegalArgumentException(
                String.format("Can not found bAdmin by ID "+updateUser.getBAdminId())
        ) );

            if(!bAdmin.getRole().equals("admin")){
                if(bAdmin.getBusinessId() != updateUser.getBusinessId() || !bAdmin.getRole().equals("business_admin") ){
                    return updateUserResponseErr;
                }
            }
        UserDTO userDTO = registerRepository.findById(updateUser.getUpdateUserId())
                .orElseThrow(() -> new IllegalArgumentException(String.format("Can not found userDto by ID "+updateUser.getUpdateUserId())));
        userDTO.setPassword(passwordEncoder.encode(updateUser.getPassword()));

        registerRepository.save(userDTO);

        UserInfo updatedUserInfo = userInfoRepository.findById(updateUser.getUpdateUserInfoId()).orElseThrow(() -> new IllegalArgumentException(
                String.format("Can not found bAdmin by ID "+updateUser.getUpdateUserInfoId())
        ) );

        updatedUserInfo.setFirstLogin(true);

        userInfoRepository.save(updatedUserInfo);
        updateUserResponse.setUpdated(true);
        updateUserResponse.setUserInfo(updatedUserInfo);
        updateUserResponse.setMessage("New password is: "+updateUser.getPassword());
        return updateUserResponse;
    }

    @Override
    public UpdateUserResponse userDoUpdateUser(String password, long userInfoId, long userId){
        UpdateUserResponse updateUserResponse = new UpdateUserResponse();
        UpdateUserResponse updateUserResponseErr = new UpdateUserResponse();
        updateUserResponseErr.setMessage("Error! Please log in again.!");
        updateUserResponseErr.setUpdated(false);
        Optional<UserDTO> userDTO = registerRepository.findById(userId);
        Optional<UserInfo> userInfo = userInfoRepository.findById(userInfoId);
        if(userDTO.isPresent() && userInfo.isPresent()){
            userDTO.orElseThrow().setPassword(passwordEncoder.encode(password));
           registerRepository.save( userDTO.orElseThrow());
             userInfo.orElseThrow().setFirstLogin(false);
             userInfoRepository.save(userInfo.orElseThrow());
            updateUserResponse.setUserInfo(userInfo.orElseThrow());
            updateUserResponse.setMessage("Password successfully changed!");
            updateUserResponse.setUpdated(true);
        }else {
            return updateUserResponseErr;
        }
        return updateUserResponse;
    }
}
