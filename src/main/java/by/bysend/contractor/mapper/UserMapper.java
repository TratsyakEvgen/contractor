package by.bysend.contractor.mapper;

import by.bysend.contractor.dto.TelegramRegistrationDTO;
import by.bysend.contractor.model.entity.AuthData;
import by.bysend.contractor.model.entity.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;


@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Mapping(target = "userInfo", source = ".")
    @Mapping(target = "authData.login", source = "telegramId")
    public abstract User telegramRegistrationToUser(TelegramRegistrationDTO telegramRegistrationDTO);

    @AfterMapping
    void updateUser(@MappingTarget User user) {
        user.getUserInfo().setUser(user);
        AuthData authData = user.getAuthData();
        authData.setUser(user);
        authData.setPassword(passwordEncoder.encode("123"));
    }

}
