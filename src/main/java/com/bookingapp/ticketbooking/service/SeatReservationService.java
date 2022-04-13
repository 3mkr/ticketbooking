package com.bookingapp.ticketbooking.service;

import java.util.Set;

import com.bookingapp.ticketbooking.domain.Reservation;
import com.bookingapp.ticketbooking.domain.SeatReservation;

public interface SeatReservationService {
    public abstract void updateSeatReservationData(Set<SeatReservation> chosenSeats, Reservation reservation);
}
