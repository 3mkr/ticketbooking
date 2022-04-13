package com.bookingapp.ticketbooking.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.bookingapp.ticketbooking.domain.Screening;

import org.springframework.data.repository.CrudRepository;

public interface ScreeningRepository extends CrudRepository<Screening, Long> {
    List<Screening> findByScreeningDayAndScreeningTimeBetweenOrderByMovieTitleAscScreeningTimeAsc(LocalDate day, LocalTime leftTime, LocalTime rightTime);
    
    Screening findTopById(Long id);
}
