package com.hotel.command.admin;

import com.hotel.command.ActionCommand;
import com.hotel.command.ConfigurationManager;
import com.hotel.command.MessageManager;
import com.hotel.entity.Booking;
import com.hotel.service.BookingService;
import com.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteBookingCommand implements ActionCommand {

    @Autowired
    private BookingService bookingService;

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        final Logger LOG = Logger.getLogger(DeleteBookingCommand.class);
        int bookingId = Integer.parseInt(request.getParameter("booking_id"));
        try {
            page = ConfigurationManager.getProperty("path.page.allBookings");
            bookingService.delete(bookingId);
            List<Booking> bookings = bookingService.getAll();
            request.setAttribute("allBooking", bookings);
            LOG.info("Booking № " + bookingId + " was deleted");
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }
}
