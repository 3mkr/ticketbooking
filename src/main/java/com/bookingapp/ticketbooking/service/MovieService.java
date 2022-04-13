package com.bookingapp.ticketbooking.service;

import java.util.List;

import com.bookingapp.ticketbooking.domain.Movie;

public interface MovieService {
    public abstract List<Movie> getMovies();
    
    public abstract void addMovie(String title);
}
