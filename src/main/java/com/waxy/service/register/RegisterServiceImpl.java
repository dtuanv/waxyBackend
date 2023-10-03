package com.waxy.service.register;


import com.waxy.database.dto.UserDTO;
import com.waxy.database.dto.UserRoleDTO;
import com.waxy.database.entity.UserInfo;
import com.waxy.database.repository.RegisterRepository;
import com.waxy.database.repository.UserInfoRepository;
import com.waxy.request.RegisterRequest;
import com.waxy.security.response.RegisterResponse;
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
        Optional<UserInfo> isUserWithNameInDb = userInfoRepository.findByNameAndBusinessId(registerRequest.getBusinessId(),registerRequest.getFullName());
        if (isUserInDb.isPresent()) {
            RegisterResponse registerResponseErr = new RegisterResponse();

            registerResponseErr.setMesssage(registerRequest.getUsername()+" has already in db. Please use another username");

            return registerResponseErr;

//            throw new RuntimeException("User has already in db");
        }
        if(isUserWithNameInDb.isPresent()){
            RegisterResponse registerResponseError = new RegisterResponse();
            switch(registerRequest.getLanguage()){
                case "vi": registerResponseError.setMesssage("Tên "+registerRequest.getFullName()+ " đã tồn tại, xin vui lòng lấy tên khác!");
                break;
                case "en":registerResponseError.setMesssage("The name '"+registerRequest.getFullName()+"' already exists. Please use another name.");
                break;
                case "de":registerResponseError.setMesssage("Der Name '"+registerRequest.getFullName()+"' existiert bereits. Bitte verwenden Sie einen anderen Namen.");
                break;
                default:
                    registerResponseError.setMesssage("There is an error. Please log in again");
            }
            return registerResponseError;
        }

        UserDTO newUser = mapNewRegisterToNewUserOrUpdateUser(registerRequest);
        registerRepository.save(newUser);
        if(registerRequest.getUserId() == 0){
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(newUser.getId());

            userInfo.setAvatar(registerRequest.getAvatar());

            userInfo.setName(registerRequest.getFullName());

            userInfo.setRestVacation(registerRequest.getRestVacation());

            userInfo.setBusinessId((long) registerRequest.getBusinessId());

            userInfo.setRole(registerRequest.getRole());

            userInfo.setDepartment(registerRequest.getDepartment());

            userInfo.setBirthday(registerRequest.getBirthday());

            userInfo.setPosition(registerRequest.getPosition());

            userInfo.setLanguage(registerRequest.getLanguage());

            userInfoRepository.save(userInfo);
        }

        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setRegistered(true);
        registerResponse.setMesssage("User was saved");

        return registerResponse;
    }

    private UserDTO mapNewRegisterToNewUserOrUpdateUser(RegisterRequest registerRequest) {
        UserDTO userDto = new UserDTO();

        userDto.setUsername(registerRequest.getUsername());
        userDto.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        UserRoleDTO basicRole = new UserRoleDTO();
        basicRole.setRole("ROL_BASIC");
        basicRole.setUserDTO(userDto);

        userDto.getUserRoles().add(basicRole);
        return userDto;
    }
}
