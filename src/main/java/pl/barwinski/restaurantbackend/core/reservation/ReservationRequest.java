package pl.barwinski.restaurantbackend.core.reservation;

import java.time.LocalDateTime;

public class ReservationRequest {
    public long reservationTableId;
    public LocalDateTime startDateTime;
    public LocalDateTime endDateTime;
}
