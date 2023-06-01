package pl.barwinski.restaurantbackend.core.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import pl.barwinski.restaurantbackend.core.reservationtable.ReservationTableEntity;
import pl.barwinski.restaurantbackend.core.reservationtable.ReservationTableService;
import pl.barwinski.restaurantbackend.core.user.UserEntity;
import pl.barwinski.restaurantbackend.core.user.UserService;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationTableService reservationTableService;
    private final UserService userService;

    public List<ReservationEntity> getReservationsBetween(LocalDateTime startDate, LocalDateTime endDate){
        return reservationRepository.findAllByStartDateTimeBetween(startDate, endDate);
    }

    public List<ReservationEntity> getTableReservationsBetween(LocalDateTime startDate, LocalDateTime endDate, Long tableId){
        return reservationRepository.findAllByStartDateTimeBetweenAndReservationTableId(startDate, endDate, tableId);
    }

    public List<ReservationTableEntity> getAvailableTables(LocalDateTime startDate, LocalDateTime endDate){
        List<ReservationEntity> reservations = getReservationsBetween(startDate, endDate);
        List<ReservationTableEntity> allTables = reservationTableService.getAll();
        for (ReservationEntity reservation : reservations) {
            allTables.remove(reservation.getReservationTable());
        }
        return allTables;
    }

    public ReservationEntity getReservationById(long id){
        return reservationRepository.findById(id).orElseThrow();
    }

    public ReservationEntity deleteReservationById(long id){
        ReservationEntity reservation = getReservationById(id);
        reservationRepository.deleteById(id);
        return reservation;
    }

    public ReservationEntity reserveTable(ReservationRequest request){
        ReservationEntity reservation = new ReservationEntity();

        ReservationTableEntity table = reservationTableService.getReservationTableById(request.reservationTableId);
        reservation.setReservationTable(table);

        List<ReservationEntity> conflictingReservations = getTableReservationsBetween(request.startDateTime,request.endDateTime,request.reservationTableId);
        if(!conflictingReservations.isEmpty())
            throw new RuntimeException("Table is already reserved");

        reservation.setStartDateTime(request.startDateTime);
        reservation.setEndDateTime(request.endDateTime);

        UserEntity user = userService.getById(request.userId);
        reservation.setUser(user);

        return reservationRepository.save(reservation);
    }
}
