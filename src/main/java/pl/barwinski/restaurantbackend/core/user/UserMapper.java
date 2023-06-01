package pl.barwinski.restaurantbackend.core.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.barwinski.restaurantbackend.core.address.AddressDto;
import pl.barwinski.restaurantbackend.core.address.AddressEntity;
import pl.barwinski.restaurantbackend.core.user.request.RegistrationRequest;

import java.util.Collections;
import java.util.List;

@Mapper
public interface UserMapper{
    UserDto mapToDto(UserEntity user);

    UserSimpleDto mapToSimpleDto(UserEntity user);

    List<UserDto> mapToDto(List<UserEntity> users);

    List<UserSimpleDto> mapToSimpleDto(List<UserEntity> users);

    UserEntity mapToEntity(UserDto userDto);

    default List<AddressEntity> mapToAddressEntityList(AddressDto addressDto) {
        if (addressDto != null) {
            AddressEntity addressEntity = new AddressEntity();

            addressEntity.setCityName(addressDto.cityName);
            addressEntity.setStreetName(addressDto.streetName);
            addressEntity.setAptNumber(addressDto.aptNumber);
            addressEntity.setStreetNumber(addressDto.streetNumber);

            return Collections.singletonList(addressEntity);
        } else {
            return Collections.emptyList();
        }
    }

    @Mapping(target = "addresses", expression = "java(mapToAddressEntityList(registrationRequestDto.address))")
    UserEntity mapToEntity(RegistrationRequest registrationRequestDto);
}