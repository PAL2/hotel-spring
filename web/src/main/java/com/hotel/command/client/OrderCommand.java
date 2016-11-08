package com.hotel.command.client;

import com.hotel.command.ActionCommand;
import com.hotel.command.ConfigurationManager;
import com.hotel.command.MessageManager;
import com.hotel.entity.User;
import com.hotel.service.BookingService;
import com.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OrderCommand implements ActionCommand {

    @Autowired
    private BookingService bookingService;

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        final Logger LOG = Logger.getLogger(OrderCommand.class);
        try {
            User user = (User) request.getSession().getAttribute("user");
            int userId = user.getUserId();
            String login = user.getLogin();
            int place = Integer.parseInt(request.getParameter("numberPlaces"));
            String category = request.getParameter("category");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String startDateString = request.getParameter("startDate");
            String endDateString = request.getParameter("endDate");
            LocalDate startDate = LocalDate.parse(startDateString, formatter);
            LocalDate endDate = LocalDate.parse(endDateString, formatter);
            if (startDate.isAfter(LocalDate.now()) && startDate.isBefore(endDate)) {
                bookingService.addBooking(startDate, endDate, userId, place, category);
                page = ConfigurationManager.getProperty("path.page.order");
                request.setAttribute("roomSuccess", MessageManager.getProperty("message.roomSuccess"));
                LOG.info("User " + login + " booked room class " + category + ", number of places: " + place
                        + ", check-in date: " + startDate + ", check-out date: " + endDate);
            } else {
                page = ConfigurationManager.getProperty("path.page.order");
                request.setAttribute("incorrectDate", MessageManager.getProperty("message.incorrectDate"));
            }
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

}
