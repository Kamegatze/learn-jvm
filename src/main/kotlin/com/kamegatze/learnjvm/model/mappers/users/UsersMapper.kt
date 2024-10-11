package com.kamegatze.learnjvm.model.mappers.users

import com.kamegatze.learnjvm.configuration.MapstructConfig
import com.kamegatze.learnjvm.model.db.users.Users
import com.kamegatze.learnjvm.model.registration.Registration
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(config = MapstructConfig::class)
interface UsersMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "iconId", ignore = true)
    @Mapping(target = "roleId", ignore = true)
    fun mapRegistrationToUser(userRegistration: Registration) : Users

    @Mapping(target = "retryPassword", ignore = true)
    fun mapUsersToRegistration(users: Users): Registration
}