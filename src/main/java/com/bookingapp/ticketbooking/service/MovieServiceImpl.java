package com.bookingapp.ticketbooking.service;

import java.util.ArrayList;
import java.util.List;

import com.bookingapp.ticketbooking.domain.Movie;
import com.bookingapp.ticketbooking.repositories.MovieRepository;

import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService{
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMovies() {
        Iterable<Movie> moviesIterable = movieRepository.findAll();
        List<Movie> moviesList = new ArrayList<>();
        for (Movie movie : moviesIterable) {
            moviesList.add(movie);
        } 
        return moviesList;
    }

    public void addMovie(String title) {
        Movie m = new Movie(title);
        movieRepository.save(m);
    }
}
