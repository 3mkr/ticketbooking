package com.bookingapp.ticketbooking.repositories;

import com.bookingapp.ticketbooking.domain.Reservation;

import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    
}
