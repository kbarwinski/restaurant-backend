package pl.barwinski.restaurantbackend.core.reservation;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    ReservationDto mapToDto(ReservationEntity reservation);
    ReservationEntity mapToEntity(ReservationDto reservationDto);
    List<ReservationDto> mapToDto(List<ReservationEntity> reservations);
    List<ReservationEntity> mapToEntity(List<ReservationDto> reservationDtos);
}
