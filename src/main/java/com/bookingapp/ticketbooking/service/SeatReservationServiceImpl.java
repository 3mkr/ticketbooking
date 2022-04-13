package com.bookingapp.ticketbooking.service;

import java.util.Set;

import com.bookingapp.ticketbooking.domain.Reservation;
import com.bookingapp.ticketbooking.domain.SeatReservation;
import com.bookingapp.ticketbooking.repositories.SeatReservationRepository;

import org.springframework.stereotype.Service;

@Service
public class SeatReservationServiceImpl implements SeatReservationService {
    private final SeatReservationRepository seatReservationRepository;

    public SeatReservationServiceImpl(SeatReservationRepository seatReservationRepository) {
        this.seatReservationRepository = seatReservationRepository;
    }

    public void updateSeatReservationData(Set<SeatReservation> chosenSeats, Reservation reservation) {
        for (SeatReservation sr : chosenSeats) {
            sr.setReservationNumber(reservation);
            seatReservationRepository.save(sr);
        }
    }
    
}
