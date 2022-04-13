package com.bookingapp.ticketbooking.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String surname;
    private BigDecimal price;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Set<SeatReservation> bookedSeats = new HashSet<>();

    public Reservation() {
    }

    public Reservation(String name, String surname, BigDecimal price) {
        this.name = name;
        this.surname = surname;
        this.price = price;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<SeatReservation> getBookedSeats() {
        return this.bookedSeats;
    }

    public void setBookedSeats(Set<SeatReservation> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Reservation)) {
            return false;
        }
        Reservation reservation = (Reservation) o;
        return id != null ? id.equals(reservation.id) : reservation.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", surname='" + getSurname() + "'" +
            "}";
    }

}
