package com.bookingapp.ticketbooking.repositories;

import com.bookingapp.ticketbooking.domain.SeatReservation;

import org.springframework.data.repository.CrudRepository;

public interface SeatReservationRepository extends CrudRepository<SeatReservation, Long> {
    
}
