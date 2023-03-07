package pl.barwinski.restaurantbackend.core.user;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import pl.barwinski.restaurantbackend.core.user.UserDto;
import pl.barwinski.restaurantbackend.core.user.UserEntity;

import java.util.List;

@Mapper
public interface UserMapper{
    UserDto mapToDto(UserEntity project);

    List<UserDto> mapToDto(List<UserEntity> projects);

    UserEntity mapToEntity(UserDto userDto);
}