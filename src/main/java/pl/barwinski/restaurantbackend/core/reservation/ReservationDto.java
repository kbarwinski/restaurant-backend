package pl.barwinski.restaurantbackend.core.reservation;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Setter;
import pl.barwinski.restaurantbackend.core.reservationtable.ReservationTableDto;
import pl.barwinski.restaurantbackend.core.reservationtable.ReservationTableEntity;
import pl.barwinski.restaurantbackend.core.user.UserDto;
import pl.barwinski.restaurantbackend.core.user.UserEntity;

import java.time.LocalDateTime;

public class ReservationDto {
    public Long id;
    public LocalDateTime startDateTime;
    public LocalDateTime endDateTime;
    public ReservationTableDto reservationTable;

    public UserDto user;
}
