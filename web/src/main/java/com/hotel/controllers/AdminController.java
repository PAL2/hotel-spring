package com.hotel.controllers;

import com.hotel.command.ConfigurationManager;
import com.hotel.command.MessageManager;
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
    public String newBookingGet(Model model) throws ServiceException {
        String page;
        try {
            page = null;
            List<Booking> bookings = bookingService.getAllNewBooking();
            model.addAttribute("newBooking", bookings);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

    @RequestMapping(value = "/newbooking", method = RequestMethod.POST)
    public String newBookingPost(Model model) throws ServiceException {
        String page;
        try {
            List<Booking> bookings = bookingService.getAllNewBooking();
            model.addAttribute("newBooking", bookings);
            page = null;
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

    @RequestMapping(value = "/allbookings", method = RequestMethod.POST)
    public String allBooking(Model model) {
        String page;
        try {
            page = null;
            /*page = ConfigurationManager.getProperty("path.page.allBookings");*/
            List<Booking> bookings = bookingService.getAll();
            model.addAttribute("allBooking", bookings);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }
}
