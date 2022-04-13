package com.bookingapp.ticketbooking.controllers;

import java.util.List;

import com.bookingapp.ticketbooking.domain.Movie;
import com.bookingapp.ticketbooking.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/movies/list")
    public List<Movie> getMovies() {
        return movieService.getMovies();
    }

    @PostMapping("/movies/add")
    public @ResponseBody String addMovie(@RequestParam String title) {
        movieService.addMovie(title);
        return "Saved";
    }    
}
