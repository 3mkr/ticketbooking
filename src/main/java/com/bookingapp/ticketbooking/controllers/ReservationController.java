package com.bookingapp.ticketbooking.controllers;

import java.util.Set;

import com.bookingapp.ticketbooking.domain.Reservation;
import com.bookingapp.ticketbooking.domain.Screening;
import com.bookingapp.ticketbooking.domain.SeatReservation;
import com.bookingapp.ticketbooking.extras.ReservationForm;
import com.bookingapp.ticketbooking.service.ReservationService;
import com.bookingapp.ticketbooking.service.ScreeningService;
import com.bookingapp.ticketbooking.service.SeatReservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ScreeningService screeningService;

    @Autowired
    private SeatReservationService seatReservationService;

    @PostMapping("/book/show/{id}")
    public String bookTickets(@PathVariable Long id, @RequestBody ReservationForm reservationForm) {
        if(!reservationService.validateData(reservationForm)) {
            return "\n\nIncorrect data!\n\n\n";
        }

        if (!screeningService.properTime(id)) {
            return "\n\nToo late, You can't book seats now.\n\n\n";
        }
        
        if(!screeningService.handleSeatReservation(id, reservationForm.getChosenSeats())) {
            return "\n\nIllegal choice of seats\n\n\n";
        }

        Screening screening = screeningService.getScreeningWithId(id);
        Set<SeatReservation> seats = screeningService.changeSRIdentification(id, reservationForm.getChosenSeats());

        Reservation reservation = reservationService.makeReservation(screening, reservationForm, seats);

        seatReservationService.updateSeatReservationData(seats, reservation);


        return  "\n\n" + reservationForm.getName() + " " + reservationForm.getSurname() +
                ", you just booked seats " + reservationForm.getChosenSeats() + '\n' +
                "Total amount to pay: " +  reservation.getPrice() + " PLN\n" +
                "Your reservation will expire: " + screeningService.getExpirationTime(id) + "\n\n\n";
    }

}
