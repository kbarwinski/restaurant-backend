package pl.barwinski.restaurantbackend.core.reservationtable;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationTableService {
    private final ReservationTableRepository reservationTableRepository;

    public ReservationTableEntity getReservationTableById(long id){
        return reservationTableRepository.findById(id).orElseThrow();
    }

    public ReservationTableEntity addReservationTable(ReservationTableEntity reservationTable){
        return reservationTableRepository.save(reservationTable);
    }

    public ReservationTableEntity updateReservationTable(ReservationTableEntity reservationTable){
        return reservationTableRepository.save(reservationTable);
    }

    public void deleteReservationTable(long id){
        reservationTableRepository.deleteById(id);
    }

    public List<ReservationTableEntity> getAll(){
        return reservationTableRepository.findAll();
    }
}
