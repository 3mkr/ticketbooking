package com.bookingapp.ticketbooking.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class, 
    property = "id")
@Entity
public class Hall {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int roomNumber;
    private int numberOfRows;
    private int numberOfColumns;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name = "hall_id")
    private List<Seat> seats = new ArrayList<>();

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name = "hall_id")
    private Set<Screening> screenings = new HashSet<>();


    public Hall() {
    }

    public Hall(int roomNumber, int numberOfRows, int numberOfColumns) {
        this.roomNumber = roomNumber;
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return this.roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getNumberOfRows() {
        return this.numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfColumns() {
        return this.numberOfColumns;
    }

    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    public List<Seat> getSeats() {
        return this.seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Set<Screening> getScreenings() {
        return this.screenings;
    }

    public void setScreenings(Set<Screening> screenings) {
        this.screenings = screenings;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Hall)) {
            return false;
        }
        Hall hall = (Hall) o;
        return id != null ? id.equals(hall.id) : hall.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", roomNumber='" + getRoomNumber() + "'" +
            ", numberOfRows='" + getNumberOfRows() + "'" +
            ", numberOfColumns='" + getNumberOfColumns() + "'" +
            //", seats=\n'" + getSeats() + "'" +
            //", screenings='" + getScreenings() + "'" +
            "}";
    }
    
}
