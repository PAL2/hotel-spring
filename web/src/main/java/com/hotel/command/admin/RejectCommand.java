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

public class RejectCommand implements ActionCommand {

    @Autowired
    private BookingService bookingService;

    @Override
    public String execute(HttpServletRequest request) {
        final Logger LOG = Logger.getLogger(RejectCommand.class);
        int bookingId = Integer.parseInt(request.getParameter("booking_id"));
        String page;
        try {
            page = ConfigurationManager.getProperty("path.page.newBooking");
            bookingService.rejectBooking(bookingId);
            List<Booking> bookings = bookingService.getAllNewBooking();
            request.setAttribute("newBooking", bookings);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        LOG.info("Administrator reject the book № " + bookingId);
        return page;
    }

}
