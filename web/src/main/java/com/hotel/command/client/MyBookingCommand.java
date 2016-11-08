package com.hotel.command.client;

import com.hotel.command.ActionCommand;
import com.hotel.command.ConfigurationManager;
import com.hotel.command.MessageManager;
import com.hotel.entity.Booking;
import com.hotel.entity.User;
import com.hotel.service.BookingService;
import com.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class MyBookingCommand implements ActionCommand {

    @Autowired
    private BookingService bookingService;

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        final Logger LOG = Logger.getLogger(MyBookingCommand.class);
        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getUserId();
        String login = user.getLogin();
        try {
            page = ConfigurationManager.getProperty("path.page.myBookings");
            List<Booking> bookings = bookingService.getAllBookingByUser(userId);
            request.setAttribute("bookingByUser", bookings);
            LOG.info("User " + login + " looked up a list of his bookings");
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

}
