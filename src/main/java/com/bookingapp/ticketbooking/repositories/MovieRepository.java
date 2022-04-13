package com.bookingapp.ticketbooking.repositories;

import java.util.List;

import com.bookingapp.ticketbooking.domain.Movie;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    List<Movie> findByTitle(String title);
}
