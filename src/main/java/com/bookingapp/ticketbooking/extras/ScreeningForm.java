package com.bookingapp.ticketbooking.extras;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

public class ScreeningForm {
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate day;

    private LocalTime leftTime;
    private LocalTime rightTime;

    public LocalDate getDay() {
        return this.day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public LocalTime getLeftTime() {
        return this.leftTime;
    }

    public void setLeftTime(LocalTime leftTime) {
        this.leftTime = leftTime;
    }

    public LocalTime getRightTime() {
        return this.rightTime;
    }

    public void setRightTime(LocalTime rightTime) {
        this.rightTime = rightTime;
    }

    @Override
    public String toString() {
        return "{" +
            " day='" + getDay() + "'" +
            ", leftTime='" + getLeftTime() + "'" +
            ", rightTime='" + getRightTime() + "'" +
            "}";
    }

}
