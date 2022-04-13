package com.bookingapp.ticketbooking.repositories;

import java.util.List;

import com.bookingapp.ticketbooking.domain.Seat;

import org.springframework.data.repository.CrudRepository;

public interface SeatRepository extends CrudRepository<Seat, Long> {
    List<Seat> findByColumnAndHallId(int column, Long hallId);

    @Override
    List<Seat> findAll();
}
