package pl.barwinski.restaurantbackend.core.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import pl.barwinski.restaurantbackend.core.reservationtable.ReservationTableEntity;
import pl.barwinski.restaurantbackend.core.reservationtable.ReservationTableService;
import pl.barwinski.restaurantbackend.core.user.UserEntity;
import pl.barwinski.restaurantbackend.core.user.UserService;

import java.time.LocalDateTime;
import java.util.HashSet;
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
        List<ReservationTableEntity> reservedTables = getReservationsBetween(startDate, endDate)
                .stream()
                .map(ReservationEntity::getReservationTable)
                .distinct()
                .toList();

        List<ReservationTableEntity> availableTables = reservationTableService.getAll();
        availableTables.removeAll(reservedTables);
        return availableTables;
    }

    public ReservationEntity getReservationById(long id){
        return reservationRepository.findById(id).orElseThrow();
    }

    public ReservationEntity deleteReservationById(long id){
        ReservationEntity reservation = getReservationById(id);
        reservationRepository.deleteById(id);
        return reservation;
    }

    public ReservationEntity reserveTable(String email, ReservationRequest request){
        ReservationEntity reservation = new ReservationEntity();

        ReservationTableEntity table = reservationTableService.getReservationTableById(request.reservationTableId);
        reservation.setReservationTable(table);

        List<ReservationEntity> conflictingReservations = getTableReservationsBetween(request.startDateTime,request.endDateTime,request.reservationTableId);
        if(!conflictingReservations.isEmpty())
            throw new RuntimeException("Table is already reserved");

        reservation.setStartDateTime(request.startDateTime);
        reservation.setEndDateTime(request.endDateTime);

        UserEntity user = userService.getByEmail(email);
        reservation.setUser(user);

        return reservationRepository.save(reservation);
    }

    public List<ReservationTableEntity> getAllTables() {
        return reservationTableService.getAll();
    }

    public List<ReservationEntity> getReservations() {
        return reservationRepository.findAll();
    }
}
