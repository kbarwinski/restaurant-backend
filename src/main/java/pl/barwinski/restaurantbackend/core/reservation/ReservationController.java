package pl.barwinski.restaurantbackend.core.reservation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.barwinski.restaurantbackend.core.reservationtable.ReservationTableDto;
import pl.barwinski.restaurantbackend.core.reservationtable.ReservationTableEntity;
import pl.barwinski.restaurantbackend.core.reservationtable.ReservationTableMapper;
import pl.barwinski.restaurantbackend.utils.EndpointPaths;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = EndpointPaths.BASE)
@Tag(name = "Reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;
    private final ReservationTableMapper reservationTableMapper;

    @GetMapping(path = EndpointPaths.EMPLOYEE + EndpointPaths.RESERVATION + "/{id}")
    @Operation(summary = "Get reservation by ID", description = "Retrieve a reservation by its ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved reservation")
    public ResponseEntity<ReservationDto> getReservation(@PathVariable Long id) {
        ReservationEntity reservation = reservationService.getReservationById(id);
        ReservationDto reservationDto = reservationMapper.mapToDto(reservation);
        return ResponseEntity.ok(reservationDto);
    }

    @GetMapping(path = EndpointPaths.EMPLOYEE + EndpointPaths.RESERVATION)
    @Operation(summary = "Get all reservations", description = "Retrieve a list of all reservations")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved reservations")
    public ResponseEntity<List<ReservationDto>> getReservations() {
        List<ReservationEntity> reservations = reservationService.getReservations();
        List<ReservationDto> reservationDtos = reservationMapper.mapToDto(reservations);
        return ResponseEntity.ok(reservationDtos);
    }

    @PostMapping(path = EndpointPaths.CLIENT + EndpointPaths.RESERVATION)
    @Operation(summary = "Create a new reservation", description = "Create a new reservation based on the request body")
    @ApiResponse(responseCode = "201", description = "Reservation created")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        ReservationEntity createdReservation = reservationService.reserveTable(email,request);
        ReservationDto reservationDto = reservationMapper.mapToDto(createdReservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationDto);
    }

    @DeleteMapping(path = EndpointPaths.EMPLOYEE + EndpointPaths.RESERVATION + "/{id}")
    @Operation(summary = "Delete reservation by ID", description = "Delete a reservation by its ID")
    @ApiResponse(responseCode = "204", description = "Reservation deleted")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Reservation not found")
    })
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservationById(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping(path = EndpointPaths.CLIENT + EndpointPaths.RESERVATION + "/tables")
    @Operation(summary = "Get available tables for reservation", description = "Retrieve a list of all tables")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved tables")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<List<ReservationTableDto>> getAllTables() {
        List<ReservationTableEntity> availableTables = reservationService.getAllTables();
        List<ReservationTableDto> tableDtos = reservationTableMapper.mapToDto(availableTables);
        return ResponseEntity.ok(tableDtos);
    }

    @GetMapping(path = EndpointPaths.CLIENT + EndpointPaths.RESERVATION + "/available-tables")
    @Operation(summary = "Get available tables for reservation", description = "Retrieve a list of available tables for a given time period")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved available tables")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<List<ReservationTableDto>> getAvailableTables(
            @RequestParam("startDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
            @RequestParam("endDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime
    ) {
        List<ReservationTableEntity> availableTables = reservationService.getAvailableTables(startDateTime, endDateTime);
        List<ReservationTableDto> tableDtos = reservationTableMapper.mapToDto(availableTables);
        return ResponseEntity.ok(tableDtos);
    }

    @GetMapping(path = EndpointPaths.CLIENT + EndpointPaths.RESERVATION + "/between-dates")
    @Operation(summary = "Get reservations between dates", description = "Retrieve a list of reservations between the specified startDateTime and endDateTime")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved reservations")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<List<ReservationDto>> getReservationsBetween(
            @RequestParam("startDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
            @RequestParam("endDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime

    )
    {
        List<ReservationEntity> reservations = reservationService.getReservationsBetween(startDateTime, endDateTime);
        return ResponseEntity.ok(reservationMapper.mapToDto(reservations));
    }
}
