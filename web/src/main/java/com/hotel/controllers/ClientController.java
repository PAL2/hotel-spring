package com.hotel.controllers;

import com.hotel.command.ConfigurationManager;
import com.hotel.entity.Account;
import com.hotel.entity.Booking;
import com.hotel.entity.User;
import com.hotel.service.AccountService;
import com.hotel.service.BookingService;
import com.hotel.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

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
    private MessageSource messageSource;

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String getLoginPage(Model model, @ModelAttribute("user") User user) {
        String page = ConfigurationManager.getProperty("path.page.order");
        Booking booking = new Booking();
        model.addAttribute("booking", booking);
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
    public String addOrder(Model model, Locale locale,
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
                page = ConfigurationManager.getProperty("path.page.order");
                model.addAttribute("roomSuccess", messageSource.getMessage("message.roomSuccess", null, locale));
            } else {
                page = ConfigurationManager.getProperty("path.page.order");
                model.addAttribute("incorrectDate", messageSource.getMessage("message.incorrectDate", null, locale));
            }
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", messageSource.getMessage("message.errorDatabase", null, locale));
        }
        return page;
    }

    @RequestMapping(value = "/mybookings", method = RequestMethod.POST)
    public String myBookings(Model model, @ModelAttribute("user") User user, Locale locale) {
        String page;
        try {
            page = null;
            List<Booking> bookings = bookingService.getAllBookingByUser(user.getUserId());
            model.addAttribute("bookingByUser", bookings);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", messageSource.getMessage("message.errorDatabase", null, locale));
        }
        return page;
    }

    @RequestMapping(value = "/unpaidaccounts", method = RequestMethod.GET)
    public String unpaidAccountsGet(Model model, @ModelAttribute("user") User user, Locale locale) {
        String page;
        try {
            page = ConfigurationManager.getProperty("path.page.myAccounts");
            List<Booking> bookings = bookingService.getAllBookingWithAccountByUser(user.getUserId());
            model.addAttribute("bookingByUser", bookings);
            List<Account> accounts = accountService.getAllAccountByUser(user.getUserId());
            model.addAttribute("accountById", accounts);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", messageSource.getMessage("message.errorDatabase", null, locale));
        }
        return page;
    }

    @RequestMapping(value = "/unpaidaccounts", method = RequestMethod.POST)
    public String unpaidAccounts(Model model, @ModelAttribute("user") User user, Locale locale) {
        String page;
        try {
            page = null;
            List<Booking> bookings = bookingService.getAllBookingWithAccountByUser(user.getUserId());
            model.addAttribute("bookingByUser", bookings);
            List<Account> accounts = accountService.getAllAccountByUser(user.getUserId());
            model.addAttribute("accountById", accounts);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", messageSource.getMessage("message.errorDatabase", null, locale));
        }
        return page;
    }

    @RequestMapping(value = "/myaccounts", method = RequestMethod.POST)
    public String myAccounts(Model model, @ModelAttribute("user") User user, Locale locale) {
        String page;
        try {
            page = null;
            List<Booking> bookings = bookingService.getAllBookingWithFinishedAccount(user.getUserId());
            model.addAttribute("bookingByUser", bookings);
            List<Account> accounts = accountService.getAllAccountByUser(user.getUserId());
            model.addAttribute("accountById", accounts);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", messageSource.getMessage("message.errorDatabase", null, locale));
        }
        return page;
    }

    @RequestMapping(value = "/myaccounts", method = RequestMethod.GET)
    public String myAccountsGet(Model model, @ModelAttribute("user") User user, Locale locale) {
        String page;
        try {
            page = null;
            List<Booking> bookings = bookingService.getAllBookingWithFinishedAccount(user.getUserId());
            model.addAttribute("bookingByUser", bookings);
            List<Account> accounts = accountService.getAllAccountByUser(user.getUserId());
            model.addAttribute("accountById", accounts);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", messageSource.getMessage("message.errorDatabase", null, locale));
        }
        return page;
    }

    @RequestMapping(value = "/unpaidaccounts", method = RequestMethod.POST, params = "id")
    public String payAccount(Model model, @RequestParam(value = "id") int bookingId,
                             @ModelAttribute("user") User user, Locale locale) {
        String page;
        try {
            bookingService.payBooking(bookingId);
            List<Booking> bookings = bookingService.getAllBookingWithFinishedAccount(user.getUserId());
            model.addAttribute("bookingByUser", bookings);
            List<Account> accounts = accountService.getAllAccountByUser(user.getUserId());
            model.addAttribute("accountById", accounts);
            model.addAttribute("paySuccess", messageSource.getMessage("message.paySuccess", null, locale));
            page = ConfigurationManager.getProperty("path.page.myAccounts");
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", messageSource.getMessage("message.errorDatabase", null, locale));
        }
        return page;
    }
}
