package com.daw.contafin.mapper;

import com.daw.contafin.dto.UnitDto;
import com.daw.contafin.dto.UserDto;
import com.daw.contafin.entity.Unit;
import com.daw.contafin.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserMapperImplTest {

    @Autowired
    private UserMapper userMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUserToUserDto() {
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        // Mock other fields if necessary

        UserDto userDto = userMapper.UserToUserDto(user);

        assertNotNull(userDto);
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getName(), userDto.getName());
        assertEquals(user.getEmail(), userDto.getEmail());
        // Add assertions for other fields if necessary
    }

    @Test
    public void testUserDtoToUser() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setName("John Doe");
        userDto.setEmail("john.doe@example.com");
        // Mock other fields if necessary

        User user = userMapper.UserDtoToUser(userDto);

        assertNotNull(user);
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getName(), user.getName());
        assertEquals(userDto.getEmail(), user.getEmail());
        // Add assertions for other fields if necessary
    }

    @Test
    public void testUsersToUsersDto() {
        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);
        List<User> users = Arrays.asList(user1, user2);

        List<UserDto> userDtos = userMapper.UsersToUsersDto(users);

        assertNotNull(userDtos);
        assertEquals(2, userDtos.size());
        assertEquals(user1.getId(), userDtos.get(0).getId());
        assertEquals(user2.getId(), userDtos.get(1).getId());
    }
}