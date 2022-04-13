package com.bookingapp.ticketbooking.bootstrap;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.bookingapp.ticketbooking.domain.Hall;
import com.bookingapp.ticketbooking.domain.Movie;
import com.bookingapp.ticketbooking.domain.Screening;
import com.bookingapp.ticketbooking.domain.Seat;
import com.bookingapp.ticketbooking.domain.SeatReservation;
import com.bookingapp.ticketbooking.repositories.HallRepository;
import com.bookingapp.ticketbooking.repositories.MovieRepository;
import com.bookingapp.ticketbooking.repositories.ScreeningRepository;
import com.bookingapp.ticketbooking.repositories.SeatRepository;
import com.bookingapp.ticketbooking.repositories.SeatReservationRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private static final int ROW_WIDTH = 12;
    private static final int SMALL_ROOM_SIZE = 1;
    private static final int MEDIUM_ROOM_SIZE = 2;
    private static final int BIG_ROOM_SIZE = 3;
    
    private final ScreeningRepository screeningRepository;
    private final MovieRepository movieRepository;
    private final SeatRepository seatRepository;
    private final HallRepository hallRepository;
    private final SeatReservationRepository seatReservationRepository;

    public BootStrapData(ScreeningRepository screeningRepository, MovieRepository movieRepository, SeatRepository seatRepository, HallRepository hallRepository, SeatReservationRepository seatReservationRepository) {
        this.screeningRepository = screeningRepository;
        this.movieRepository = movieRepository;
        this.seatRepository = seatRepository;
        this.hallRepository = hallRepository;
        this.seatReservationRepository = seatReservationRepository;
    }

    private void prepRoom(Hall room) {
        int numberOfColumns = room.getNumberOfColumns(), numberOfRows = room.getNumberOfRows();
        int numberOfSeats = numberOfColumns * numberOfRows;
        for (int i = 0; i < numberOfSeats; i++) {
            Seat s = new Seat(((i - (i % ROW_WIDTH)) / numberOfColumns) + 1, (i % ROW_WIDTH) + 1);
            room.getSeats().add(s);
            s.setHall(room);
            seatRepository.save(s);
        }
        hallRepository.save(room);
    }

    private Screening prepScreening(LocalDate screeningDay, LocalTime screeningTime, Hall room, Movie movie) {
        Screening screening = new Screening(screeningDay, screeningTime);
        screeningRepository.save(screening);

        screening.setHall(room);
        screening.setMovie(movie);

        List<SeatReservation> seatsRes = screening.prepareReservations();
        for (SeatReservation s : seatsRes) {
            seatReservationRepository.save(s);
        }
        screening.setReservations(seatsRes);
        screeningRepository.save(screening);

        movie.getScreenings().add(screening);
        movieRepository.save(movie);

        return screening;
    }

    @Override
    public void run(String... args) throws Exception {
        /*
         * Sample rooms
         */
        Hall room_one = new Hall(1, SMALL_ROOM_SIZE, ROW_WIDTH);
        hallRepository.save(room_one);
        prepRoom(room_one);

        Hall room_two = new Hall(2, MEDIUM_ROOM_SIZE, ROW_WIDTH);
        hallRepository.save(room_two);
        prepRoom(room_two);

        Hall room_three = new Hall(3, BIG_ROOM_SIZE, ROW_WIDTH);
        hallRepository.save(room_three);
        prepRoom(room_three);

        /*
         * Sample movies
         */
        Movie example_movie1 = new Movie("The Mummy Returns");
        movieRepository.save(example_movie1);

        Movie example_movie2 = new Movie("Indiana Jones and the Last Crusade");
        movieRepository.save(example_movie2);

        Movie example_movie3 = new Movie("Mamma Mia");
        movieRepository.save(example_movie3);

        /*
         * Sample screenings
         */
        Screening example_screening1 = prepScreening(LocalDate.parse("2022-04-21"), LocalTime.parse("17:30"), room_one, example_movie1);
        Screening example_screening2 = prepScreening(LocalDate.parse("2022-04-21"), LocalTime.parse("20:30"), room_two, example_movie1);
        Screening example_screening3 = prepScreening(LocalDate.parse("2022-04-21"), LocalTime.parse("11:15"), room_three, example_movie1);
        Screening example_screening4 = prepScreening(LocalDate.parse("2022-04-22"), LocalTime.parse("13:00"), room_one, example_movie3);
        Screening example_screening5 = prepScreening(LocalDate.parse("2022-04-22"), LocalTime.parse("17:30"), room_two, example_movie2);
        Screening example_screening6 = prepScreening(LocalDate.parse("2022-04-22"), LocalTime.parse("17:45"), room_three, example_movie1);

        System.out.println("Data loaded");
    }
}
