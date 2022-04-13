package com.bookingapp.ticketbooking.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.bookingapp.ticketbooking.extras.SeatState;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class, 
        property = "id")
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    LocalTime screeningTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate screeningDay;

    @JsonManagedReference
    @ManyToOne
    private Movie movie;

    @JsonManagedReference
    @ManyToOne
    private Hall hall;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name = "screening_id")
    private List<SeatReservation> reservations = new ArrayList<>();

    public Screening() {
    }

    public Screening(LocalDate screeningDay, LocalTime screeningTime) {
        this.screeningTime = screeningTime;
        this.screeningDay = screeningDay;
    }

    public LocalTime getScreeningTime() {
        return this.screeningTime;
    }

    public void setScreeningTime(LocalTime screeningTime) {
        this.screeningTime = screeningTime;
    }

    public LocalDate getScreeningDay() {
        return this.screeningDay;
    }

    public void setScreeningDay(LocalDate screeningDay) {
        this.screeningDay = screeningDay;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Hall getHall() {
        return this.hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public List<SeatReservation> getReservations() {
        return this.reservations;
    }

    public void setReservations(List<SeatReservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Screening)) {
            return false;
        }
        Screening screening = (Screening) o;
        return id != null ? id.equals(screening.id) : screening.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", screeningTime='" + getScreeningTime() + "'" +
            ", screeningDay='" + getScreeningDay() + "'" +
            //", movie='" + getMovie() + "'" +
            ", hall='" + getHall() + "'" +
            //", reservations='" + getReservations() + "'" +
            "}";
    }

    public List<SeatReservation> prepareReservations() {
        List<SeatReservation> res = new ArrayList<>();

        for (Seat s : this.hall.getSeats()) {
            SeatReservation sr = new SeatReservation(this, s);
            res.add(sr);
        }

        return res;
    }

    private boolean validateData(Set<Integer> chosenSeats, int numberOfSeats) {
        if (chosenSeats.size() == 0) {
            return false;
        }

        for (int i : chosenSeats) {
            if (i >= numberOfSeats) {
                return false;
            }
        }

        return true;
    }

    private void cleanChoice(Set<Integer> chosenSeats) {
        for (int i : chosenSeats) {
            this.reservations.get(i).setSeatTaken(SeatState.AVAILABLE);
        }
    }

    /*
     * This function gets a set of seats' indexes chosen by the user and checks if that choice of seats
     * is legal. If it is, then is marks them as taken (SeatStatus.TAKEN) and returns true. Otherwise,
     * it returns false, which should lead to rendering proper message.
     * I'm assuming that the user can't try to reserve taken seat, because that possibility should be blocked
     * by the UI.
     * I'm also assuming that leaving single seat on the end of the row is just as bad, as leaving
     * a single seat between reservations and that is also illegal.
     */ 
    public boolean reserveTickets(Set<Integer> chosenSeats) {
        int numberOfColumns = this.getHall().getNumberOfColumns();
        int numberOfSeats = this.getHall().getNumberOfColumns() * this.getHall().getNumberOfRows();

        if(!validateData(chosenSeats, numberOfSeats)) {
            return false;
        }

        for (int i : chosenSeats) {
            this.reservations.get(i).setSeatTaken(SeatState.CHOSEN);
        }

        for (int i : chosenSeats) {
            int positionInRow = i % numberOfColumns;
            if (positionInRow < numberOfColumns - 2) {      // Case: ... [chosen_seat] [available_seat] [taken_seat]
                if (this.reservations.get(i + 1).getSeatTaken() == SeatState.AVAILABLE &&
                        this.reservations.get(i + 2).getSeatTaken() != SeatState.AVAILABLE) {
                    this.cleanChoice(chosenSeats);
                    return false;
                }
            }
            if (positionInRow == numberOfColumns - 2) {     // Case: ... [chosen_seat] [available_seat] END_OF_ROW
                if (this.reservations.get(i + 1).getSeatTaken() == SeatState.AVAILABLE) {
                    this.cleanChoice(chosenSeats);
                    return false;
                }
            }
            if (positionInRow > 1) {                        // Case: [taken_seat] [available_seat] [chosen_seat] ... 
                if (this.reservations.get(i - 1).getSeatTaken() == SeatState.AVAILABLE &&
                        this.reservations.get(i - 2).getSeatTaken() != SeatState.AVAILABLE) {
                    this.cleanChoice(chosenSeats);
                    return false;
                }
            }
            if (positionInRow == 1) {                       // Case: END_OF ROW [available_seat] [chosen_seat]
                if (this.reservations.get(i - 1).getSeatTaken() == SeatState.AVAILABLE) {
                    this.cleanChoice(chosenSeats);
                    return false;
                }
            }
        }

        for (int i : chosenSeats) {
            this.reservations.get(i).setSeatTaken(SeatState.TAKEN);
        }

        return true;
    }
}
