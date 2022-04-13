package com.bookingapp.ticketbooking.repositories;

import com.bookingapp.ticketbooking.domain.Hall;

import org.springframework.data.repository.CrudRepository;

public interface HallRepository extends CrudRepository<Hall, Long> {
    
}
