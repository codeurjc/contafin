package com.daw.contafin.user;

import com.daw.contafin.unit.Unit;
import com.daw.contafin.unit.UnitDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;


@Mapper( componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserMapper {

    UserDto UserToUserDto(User user);
    User UserDtoToUser(UserDto userDto);

    List<UserDto> UsersToUsersDto(Collection<User> users);
    List<User> UsersDtoToUsers(Collection<UserDto> usersDto);
}