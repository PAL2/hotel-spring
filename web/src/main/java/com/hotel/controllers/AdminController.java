package com.hotel.controllers;

import com.hotel.entity.Booking;
import com.hotel.service.BookingService;
import com.hotel.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Алексей on 13.11.2016.
 */

@org.springframework.stereotype.Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/newbooking", method = RequestMethod.GET)
    public String newBooking(Model model) throws ServiceException {
        List<Booking> bookings = bookingService.getAllNewBooking();
        model.addAttribute("newBooking", bookings);
        return "admin/newbooking";
    }
}
