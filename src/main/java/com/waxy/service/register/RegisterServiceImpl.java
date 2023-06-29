package com.waxy.service.register;


import com.waxy.database.dto.UserDTO;
import com.waxy.database.dto.UserRoleDTO;
import com.waxy.database.entity.UserInfo;
import com.waxy.database.repository.RegisterRepository;
import com.waxy.database.repository.UserInfoRepository;
import com.waxy.request.RegisterRequest;
import com.waxy.response.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service

public class RegisterServiceImpl implements RegisterService {
    private final PasswordEncoder passwordEncoder;
    private final RegisterRepository registerRepository;

    private final UserInfoRepository userInfoRepository;
    public RegisterServiceImpl(@Autowired PasswordEncoder passwordEncoder, @Autowired RegisterRepository registerRepository,@Autowired UserInfoRepository userInfoRepository) {
        this.passwordEncoder = passwordEncoder;
        this.registerRepository = registerRepository;
        this.userInfoRepository = userInfoRepository;
    }

    @Transactional
    @Override
    public RegisterResponse doRegister(RegisterRequest registerRequest) {
        Optional<UserDTO> isUserInDb = registerRepository.findByUsername(registerRequest.getUsername());
        if (isUserInDb.isPresent()) {

            RegisterResponse registerResponseErr = new RegisterResponse();

            registerResponseErr.setMesssage("User has already in db");

            return registerResponseErr;

//            throw new RuntimeException("User has already in db");

        }

        UserDTO newUser = mapNewRegisterToNewUser(registerRequest);
        registerRepository.save(newUser);
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setRegistered(true);
        registerResponse.setMesssage("User was saved");
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(newUser.getId());

        userInfo.setAvatar(registerRequest.getAvatar());

        userInfo.setName(registerRequest.getFullName());

        userInfo.setRestVacation(registerRequest.getRestVacation());

        userInfoRepository.save(userInfo);
        return registerResponse;
    }

    private UserDTO mapNewRegisterToNewUser(RegisterRequest registerRequest) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(registerRequest.getUsername());
        userDTO.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        UserRoleDTO basicRole = new UserRoleDTO();
        basicRole.setRole("ROL_BASIC");
        basicRole.setUserDTO(userDTO);

        userDTO.getUserRoles().add(basicRole);
        return userDTO;
    }
}
