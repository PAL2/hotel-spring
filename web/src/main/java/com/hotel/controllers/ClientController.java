package com.hotel.controllers;

import com.hotel.command.ConfigurationManager;
import com.hotel.command.MessageManager;
import com.hotel.entity.Account;
import com.hotel.entity.Booking;
import com.hotel.entity.User;
import com.hotel.service.AccountService;
import com.hotel.service.BookingService;
import com.hotel.service.RoomService;
import com.hotel.service.UserService;
import com.hotel.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

/**
 * Created by Алексей on 15.11.2016.
 */

@org.springframework.stereotype.Controller
@SessionAttributes("user")
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String getLoginPage(Model model, @ModelAttribute("user") User user) {
        String page = ConfigurationManager.getProperty("path.page.order");
        Booking booking = new Booking();
        model.addAttribute("booking", booking);
        System.out.println(booking);
        System.out.println(user);
        System.out.println("GET");
        return page;
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String addOrder(Model model, @ModelAttribute("booking") Booking booking,
                           @ModelAttribute("user") User user) throws ServiceException {
        String page;
        System.out.println("POST");
        System.out.println(user);
        System.out.println(booking);
        model.addAttribute("booking", booking);
        /*try {
            int userId = user.getUserId();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String startDateString = request.getParameter("startDate");
            String endDateString = request.getParameter("endDate");
            LocalDate startDate = LocalDate.parse(startDateString, formatter);
            LocalDate endDate = LocalDate.parse(endDateString, formatter);
            if (startDate.isAfter(LocalDate.now()) && startDate.isBefore(endDate)) {
                bookingService.addBooking(startDate, endDate, userId, place, category);
                page = ConfigurationManager.getProperty("path.page.order");
                request.setAttribute("roomSuccess", MessageManager.getProperty("message.roomSuccess"));
            } else {
                page = ConfigurationManager.getProperty("path.page.order");
                request.setAttribute("incorrectDate", MessageManager.getProperty("message.incorrectDate"));
            }
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }*/
        return "order";
    }

    @RequestMapping(value = "/mybookings", method = RequestMethod.POST)
    public String myBookings(Model model, @ModelAttribute("user") User user) {
        String page;
        try {
            page = null;
            List<Booking> bookings = bookingService.getAllBookingByUser(user.getUserId());
            model.addAttribute("bookingByUser", bookings);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

    @RequestMapping(value = "/unpaidaccounts", method = RequestMethod.POST)
    public String unpaidAccounts(Model model, @ModelAttribute("user") User user) {
        String page;
        try {
            page = null;
            List<Booking> bookings = bookingService.getAllBookingWithAccountByUser(user.getUserId());
            model.addAttribute("bookingByUser", bookings);
            List<Account> accounts = accountService.getAllAccountByUser(user.getUserId());
            model.addAttribute("accountById", accounts);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

    @RequestMapping(value = "/myaccounts", method = RequestMethod.POST)
    public String myAccounts(Model model, @ModelAttribute("user") User user) {
        String page;
        try {
            page = null;
            List<Booking> bookings = bookingService.getAllBookingWithFinishedAccount(user.getUserId());
            model.addAttribute("bookingByUser", bookings);
            List<Account> accounts = accountService.getAllAccountByUser(user.getUserId());
            model.addAttribute("accountById", accounts);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }
}
