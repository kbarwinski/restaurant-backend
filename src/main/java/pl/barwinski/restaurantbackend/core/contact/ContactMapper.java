package pl.barwinski.restaurantbackend.core.contact;

import org.mapstruct.Mapper;

@Mapper
public interface ContactMapper{
    ContactDto mapToDto(ContactEntity project);
    ContactEntity mapToEntity(ContactDto ContactDto);
}