package pl.barwinski.restaurantbackend.core.reservationtable;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ReservationTableMapper {
    ReservationTableDto mapToDto(ReservationTableEntity reservationTable);
    ReservationTableEntity mapToEntity(ReservationTableDto reservationTableDto);
    List<ReservationTableDto> mapToDto(List<ReservationTableEntity> reservationTables);
    List<ReservationTableEntity> mapToEntity(List<ReservationTableDto> reservationTableDtos);
}
