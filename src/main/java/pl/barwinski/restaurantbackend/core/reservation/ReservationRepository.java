package pl.barwinski.restaurantbackend.core.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    List<ReservationEntity> findAllByStartDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<ReservationEntity> findAllByStartDateTimeBetweenAndReservationTableId(LocalDateTime startDate, LocalDateTime endDate, Long tableId);
}
