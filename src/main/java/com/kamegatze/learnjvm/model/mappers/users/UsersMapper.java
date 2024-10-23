package com.kamegatze.learnjvm.model.mappers.users;

import com.kamegatze.learnjvm.configuration.MapstructConfig;
import com.kamegatze.learnjvm.model.db.users.Users;
import com.kamegatze.learnjvm.model.registration.Registration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapstructConfig.class)
public interface UsersMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "icons", ignore = true)
    Users mapRegistrationToUser(Registration userRegistration);

    @Mapping(target = "retryPassword", ignore = true)
    Registration mapUsersToRegistration(Users users);
}
