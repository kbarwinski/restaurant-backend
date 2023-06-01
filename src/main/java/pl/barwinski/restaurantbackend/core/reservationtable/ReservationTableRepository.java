package pl.barwinski.restaurantbackend.core.reservationtable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationTableRepository extends JpaRepository<ReservationTableEntity, Long> {
}
