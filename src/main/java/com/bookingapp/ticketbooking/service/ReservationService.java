package com.bookingapp.ticketbooking.service;

import java.util.Set;

import com.bookingapp.ticketbooking.domain.Reservation;
import com.bookingapp.ticketbooking.domain.Screening;
import com.bookingapp.ticketbooking.domain.SeatReservation;
import com.bookingapp.ticketbooking.extras.ReservationForm;

public interface ReservationService {
    public abstract boolean validateData(ReservationForm reservationForm);

    public abstract Reservation makeReservation(Screening screening, ReservationForm reservationForm, Set<SeatReservation> seats);

    public abstract void makeNewReservation(Reservation reservation, Set<SeatReservation> seats);
}
