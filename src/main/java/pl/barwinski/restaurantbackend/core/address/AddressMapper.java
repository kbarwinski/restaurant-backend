package pl.barwinski.restaurantbackend.core.address;

import org.mapstruct.Mapper;
import java.util.List;

@Mapper
public interface AddressMapper{
    AddressEntity mapToEntity(AddressDto addressDto);

    AddressDto mapToDto(AddressEntity address);

    List<AddressDto> mapToDto(List<AddressEntity> addresses);

    List<AddressEntity> mapToEntity(List<AddressDto> addressDtos);
}