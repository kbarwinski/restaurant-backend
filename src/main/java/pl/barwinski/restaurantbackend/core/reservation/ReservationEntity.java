package pl.barwinski.restaurantbackend.core.reservation;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import pl.barwinski.restaurantbackend.core.reservationtable.ReservationTableEntity;
import pl.barwinski.restaurantbackend.core.user.UserEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@Table(name = "reservations")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationEntity {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private LocalDateTime startDateTime;

    @NotNull
    private LocalDateTime endDateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reservation_table_id")
    private ReservationTableEntity reservationTable;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}


