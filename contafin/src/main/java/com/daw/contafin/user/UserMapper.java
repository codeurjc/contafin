package com.daw.contafin.user;

import com.daw.contafin.unit.Unit;
import com.daw.contafin.unit.UnitDto;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper( componentModel = "spring")
public interface UserMapper {

    UserDto UserToUserDto(User user);
    User UserDtoToUser(UserDto userDto);

    List<UserDto> UsersToUsersDto(Collection<User> users);
    List<User> UsersDtoToUsers(Collection<UserDto> usersDto);
}
