package com.bookingapp.ticketbooking.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bookingapp.ticketbooking.domain.Screening;
import com.bookingapp.ticketbooking.domain.SeatReservation;
import com.bookingapp.ticketbooking.repositories.ScreeningRepository;

import org.springframework.stereotype.Service;

@Service
public class ScreeningServiceImpl implements ScreeningService {
private final static int QUARTER_HOUR = 15;

    private final ScreeningRepository screeningRepository;

    public ScreeningServiceImpl(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    private List<Screening> formatList(Iterable<Screening> screeningsIterable) {
        List<Screening> screeningsList = new ArrayList<>();
        for (Screening screening : screeningsIterable) {
            screeningsList.add(screening);
        }
        return screeningsList;
    }

    public List<Screening> getScreenings() {    
        return formatList(screeningRepository.findAll());
    }

    public List<Screening> getScreeningsInTime(LocalDate day, LocalTime leftTime, LocalTime rightTime) {
        return formatList(screeningRepository.findByScreeningDayAndScreeningTimeBetweenOrderByMovieTitleAscScreeningTimeAsc(day, leftTime, rightTime));
    }

    public Screening getScreeningWithId(Long id) {
        return screeningRepository.findTopById(id);
    }

    public boolean handleSeatReservation(Long id, Set<Integer> chosenSeats) {
        Screening screening = screeningRepository.findTopById(id);
        if (screening.reserveTickets(chosenSeats)) {
            screeningRepository.save(screening);
            return true;
        }

        return false; 
    }

    public Set<SeatReservation> changeSRIdentification(Long id, Set<Integer> chosenSeats) {
        Screening screening = screeningRepository.findTopById(id);
        Set<SeatReservation> seats = new HashSet<>();
        
        for (int i : chosenSeats) {
            seats.add(screening.getReservations().get(i));
        }

        return seats;
    }

    public String getExpirationTime(Long id) {
        Screening screening = screeningRepository.findTopById(id);
        
        LocalDate day = screening.getScreeningDay();
        LocalTime expirationTime = screening.getScreeningTime().minusMinutes(QUARTER_HOUR);

        return day.toString() + ' ' + expirationTime.toString();
    }

    public boolean properTime(Long id) {
        Screening screening = screeningRepository.findTopById(id);

        LocalDate screeningDay = screening.getScreeningDay();
        LocalTime blockingTime = screening.getScreeningTime().minusMinutes(QUARTER_HOUR);
        LocalDate currentDay = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        if (currentDay.equals(screeningDay) && blockingTime.compareTo(currentTime) < 0) {
            return false;
        }

        return true;
    }
    
}
