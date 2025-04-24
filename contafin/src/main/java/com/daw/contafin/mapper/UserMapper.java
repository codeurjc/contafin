package com.daw.contafin.mapper;

import com.daw.contafin.entity.User;
import com.daw.contafin.dto.UserDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper( componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserMapper {

    UserDto UserToUserDto(User user);
    User UserDtoToUser(UserDto userDto);

    List<UserDto> UsersToUsersDto(Collection<User> users);
}
