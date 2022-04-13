package com.bookingapp.ticketbooking.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.bookingapp.ticketbooking.extras.SeatState;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class, 
        property = "id")
public class SeatReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private SeatState seatTaken;

    @JsonBackReference
    @ManyToOne
    private Seat seat;

    @ManyToOne
    private Screening screening;

    @JsonBackReference
    @ManyToOne
    private Reservation reservationNumber;

    public SeatReservation() {
    }

    public SeatReservation(Screening screening, Seat seat) {
        this.seat = seat;
        this.screening = screening;
        this.seatTaken = SeatState.AVAILABLE;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Seat getSeat() {
        return this.seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Screening getScreening() {
        return this.screening;
    }

    public void setScreening(Screening screenings) {
        this.screening = screenings;
    }

    public SeatState getSeatTaken() {
        return this.seatTaken;
    }

    public void setSeatTaken(SeatState seatTaken) {
        this.seatTaken = seatTaken;
    }

    public Reservation getReservationNumber() {
        return this.reservationNumber;
    }

    public void setReservationNumber(Reservation reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SeatReservation)) {
            return false;
        }
        SeatReservation seatReservation = (SeatReservation) o;
        return id != null ? id.equals(seatReservation.id) : seatReservation.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", seatTaken='" + getSeatTaken() + "'" +
            ", seat='" + getSeat() + "'" +
            //", screening='" + getScreening() + "'" +
            "}\n";
    }

}
