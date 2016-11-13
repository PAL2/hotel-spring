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

public class BillCommand implements ActionCommand {

    @Autowired
    private BookingService bookingService;

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        final Logger LOG = Logger.getLogger(BillCommand.class);
        int roomId = Integer.parseInt(request.getParameter("room_id"));
        int bookingId = (int) request.getSession().getAttribute("booking_id");
        try {
            page = ConfigurationManager.getProperty("path.page.newBooking");
            bookingService.chooseRoom(bookingId, roomId);
            List<Booking> bookings = bookingService.getAllNewBooking();
            request.setAttribute("newBooking", bookings);
            LOG.info("Booking № " + bookingId + " confirmed and sent account");
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }
}
