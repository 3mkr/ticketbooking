package com.bookingapp.ticketbooking.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import com.bookingapp.ticketbooking.domain.Screening;
import com.bookingapp.ticketbooking.domain.SeatReservation;

public interface ScreeningService {
    public abstract List<Screening> getScreenings();

    public abstract List<Screening> getScreeningsInTime(LocalDate day, LocalTime leftTime, LocalTime rightTime);

    public abstract Screening getScreeningWithId(Long id);

    public abstract boolean handleSeatReservation(Long id, Set<Integer> chosenSeats);

    public abstract Set<SeatReservation> changeSRIdentification(Long id, Set<Integer> chosenSeats);

    public abstract String getExpirationTime(Long id);

    public abstract boolean properTime(Long id);
}
