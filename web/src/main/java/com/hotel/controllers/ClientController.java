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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        Map<Integer, Integer> place = new LinkedHashMap<Integer, Integer>();
        place.put(1, 1);
        place.put(2, 2);
        place.put(3, 3);
        place.put(4, 4);
        model.addAttribute("numberPlaces", place);
        System.out.println(booking);
        System.out.println(user);
        System.out.println("GET");

        Map<String, String> cat = new LinkedHashMap<>();
        cat.put("standard", "Standard");
        cat.put("lux", "Lux");
        model.addAttribute("cat", cat);
        return page;
    }

    @RequestMapping(value = "/gotoorder", method = RequestMethod.GET)
    public String goToOrder(Model model, @ModelAttribute("user") User user) {
        String page = ConfigurationManager.getProperty("path.page.order.red");
        Booking booking = new Booking();
        model.addAttribute("booking", booking);
        return page;
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String addOrder(Model model,
                           @RequestParam(value = "startDate") String startDateString,
                           @RequestParam(value = "numberPlaces") int place,
                           @RequestParam(value = "endDate") String endDateString,
                           @RequestParam(value = "category") String category,
                           @ModelAttribute("user") User user) throws ServiceException {
        String page;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(startDateString, formatter);
            LocalDate endDate = LocalDate.parse(endDateString, formatter);
            if (startDate.isAfter(LocalDate.now()) && startDate.isBefore(endDate)) {
                bookingService.addBooking(startDate, endDate, user.getUserId(), place, category);
                page = ConfigurationManager.getProperty("path.page.order.red");
                model.addAttribute("roomSuccess", MessageManager.getProperty("message.roomSuccess"));
            } else {
                page = ConfigurationManager.getProperty("path.page.order.red");
                model.addAttribute("incorrectDate", MessageManager.getProperty("message.incorrectDate"));
            }
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return "client/order";
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

    @RequestMapping(value = "/unpaidaccounts", method = RequestMethod.GET)
    public String unpaidAccountsGet(Model model, @ModelAttribute("user") User user) {
        String page;
        try {
            page = ConfigurationManager.getProperty("path.page.myAccounts");
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

    @RequestMapping(value = "/myaccounts", method = RequestMethod.GET)
    public String myAccountsGet(Model model, @ModelAttribute("user") User user) {
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

    @RequestMapping(value = "/unpaidaccounts", method = RequestMethod.POST, params = "id")
    public String payAccount(Model model, @RequestParam(value = "id") int bookingId,
                             @ModelAttribute("user") User user) {
        String page;
        try {
            bookingService.payBooking(bookingId);
            List<Booking> bookings = bookingService.getAllBookingWithFinishedAccount(user.getUserId());
            model.addAttribute("bookingByUser", bookings);
            List<Account> accounts = accountService.getAllAccountByUser(user.getUserId());
            model.addAttribute("accountById", accounts);
            //model.addAttribute("paySuccess", MessageManager.getProperty("message.paySuccess"));
            page = ConfigurationManager.getProperty("path.page.myAccounts");
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }
}