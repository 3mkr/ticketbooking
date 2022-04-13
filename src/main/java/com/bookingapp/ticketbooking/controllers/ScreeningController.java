package com.bookingapp.ticketbooking.controllers;

import java.util.List;

import com.bookingapp.ticketbooking.domain.Screening;
import com.bookingapp.ticketbooking.extras.ScreeningForm;
import com.bookingapp.ticketbooking.service.ScreeningService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScreeningController {
    @Autowired
    private ScreeningService screeningService;

    @GetMapping("/screenings/list")
    public List<Screening> getScreenings() {
        return screeningService.getScreenings();
    }

    @PostMapping("/screenings/find")
    public List<Screening> findScreenings(@RequestBody ScreeningForm screeningForm) {
        return screeningService.getScreeningsInTime(screeningForm.getDay(), screeningForm.getLeftTime(), screeningForm.getRightTime());
    }

    @GetMapping("/screenings/show/{id}")
    public Screening showTicketForm(@PathVariable Long id) {
        return screeningService.getScreeningWithId(id);
    }

}
