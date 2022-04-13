package com.bookingapp.ticketbooking.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class, 
    property = "id")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int rowNo;
    private int column;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name = "seat_id")
    private List<SeatReservation> seatReservations = new ArrayList<>();

    @JsonManagedReference
    @ManyToOne
    private Hall hall;

    public Seat() {
    }

    public Seat(int rowNo, int column) {
        this.rowNo = rowNo;
        this.column = column;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRowNo() {
        return this.rowNo;
    }

    public void setRowNo(int rowNo) {
        this.rowNo = rowNo;
    }

    public int getColumn() {
        return this.column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Hall getHall() {
        return this.hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Seat)) {
            return false;
        }
        Seat seat = (Seat) o;
        return id != null ? id.equals(seat.id) : seat.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", rowNo='" + getRowNo() + "'" +
            ", column='" + getColumn() + "'" +
            "}\n";
    }
    
}
