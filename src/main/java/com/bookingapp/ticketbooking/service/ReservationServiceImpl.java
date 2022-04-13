package com.bookingapp.ticketbooking.service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import com.bookingapp.ticketbooking.domain.Reservation;
import com.bookingapp.ticketbooking.domain.Screening;
import com.bookingapp.ticketbooking.domain.SeatReservation;
import com.bookingapp.ticketbooking.extras.ReservationForm;
import com.bookingapp.ticketbooking.extras.TicketType;
import com.bookingapp.ticketbooking.repositories.ReservationRepository;

import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;

    private final BigDecimal ADULT_PRICE = new BigDecimal(25);
    private final BigDecimal STUDENT_PRICE = new BigDecimal(18);
    private final BigDecimal CHILD_PRICE = new BigDecimal(12.5);
    private final int ZERO = 0;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public boolean validateData(ReservationForm reservationForm) {
        String nameRegex = "^\\p{Lu}\\p{Ll}\\p{Ll}+$";
        String surNameRegex = "^(\\p{Lu}\\p{Ll}\\p{Ll}+)|(\\p{Lu}\\p{Ll}+)(?:-)(\\p{Lu}\\p{Ll}+)";

        if (reservationForm.getName().matches(nameRegex) && reservationForm.getSurname().matches(surNameRegex)) {
            return true;
        }

        return false;
    }

    public Reservation makeReservation(Screening screenig, ReservationForm reservationForm, Set<SeatReservation> seats) {
        BigDecimal price = countPrice(reservationForm.getTicketTypes());
        
        Reservation r = new Reservation(reservationForm.getName(), reservationForm.getSurname(), price);
        reservationRepository.save(r);

        r.setBookedSeats(seats);
        reservationRepository.save(r);
        return r;
    }

    private BigDecimal countPrice(Map<Integer, TicketType> tickets) {
        BigDecimal sum = new BigDecimal(ZERO);
        for (Map.Entry<Integer, TicketType> entry : tickets.entrySet()) {
            switch(entry.getValue()) {
                case ADULT:
                    sum = sum.add(ADULT_PRICE);
                    break;
                case STUDENT:
                    sum = sum.add(STUDENT_PRICE);
                    break;
                case CHILD:
                    sum = sum.add(CHILD_PRICE);
                    break;
            }
        }
        return sum;
    }

    public void makeNewReservation(Reservation reservation, Set<SeatReservation> seats) {
        reservationRepository.save(reservation);
        
        reservation.setBookedSeats(seats);

        reservationRepository.save(reservation);
    }

}
