package com.hotel.command.client;

import com.hotel.command.ActionCommand;
import com.hotel.command.ConfigurationManager;
import com.hotel.command.MessageManager;
import com.hotel.entity.Account;
import com.hotel.entity.Booking;
import com.hotel.entity.User;
import com.hotel.service.AccountService;
import com.hotel.service.BookingService;
import com.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PayCommand implements ActionCommand {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private AccountService accountService;

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        final Logger LOG = Logger.getLogger(PayCommand.class);
        try {
            int bookingId = Integer.parseInt(request.getParameter("booking_id"));
            User user = (User) request.getSession().getAttribute("user");
            int userId = user.getUserId();
            String login = user.getLogin();
            bookingService.payBooking(bookingId);
            List<Booking> bookings = bookingService.getAllBookingWithFinishedAccount(userId);
            request.setAttribute("bookingByUser", bookings);
            List<Account> accounts = accountService.getAllAccountByUser(userId);
            request.setAttribute("accountById", accounts);
            request.setAttribute("paySuccess", MessageManager.getProperty("message.paySuccess"));
            page = ConfigurationManager.getProperty("path.page.myAccounts");
            LOG.info("User " + login + " paid his booking № " + bookingId);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

}
