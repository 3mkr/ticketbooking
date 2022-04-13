package com.bookingapp.ticketbooking.extras;

import java.util.Map;
import java.util.Set;

public class ReservationForm {
    private String name;
    private String surname;
    private Set<Integer> chosenSeats;
    private Map<Integer, TicketType> ticketTypes;


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

    public Set<Integer> getChosenSeats() {
        return this.chosenSeats;
    }

    public void setChosenSeats(Set<Integer> chosenSeats) {
        this.chosenSeats = chosenSeats;
    }

    public Map<Integer,TicketType> getTicketTypes() {
        return this.ticketTypes;
    }

    public void setTicketTypes(Map<Integer,TicketType> ticketTypes) {
        this.ticketTypes = ticketTypes;
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", surname='" + getSurname() + "'" +
            ", chosenSeats='" + getChosenSeats() + "'" +
            ", ticketTypes='" + getTicketTypes() + "'" +
            "}";
    }

}
