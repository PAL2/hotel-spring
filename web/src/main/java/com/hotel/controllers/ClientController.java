package com.hotel.controllers;

import com.hotel.entity.Account;
import com.hotel.entity.Booking;
import com.hotel.manager.ConfigurationManager;
import com.hotel.service.AccountService;
import com.hotel.service.BookingService;
import com.hotel.service.UserService;
import com.hotel.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

/**
 * Created by Алексей on 15.11.2016.
 */

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return ConfigurationManager.getProperty("path.page.order.red");
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String getLoginPage() {
        return ConfigurationManager.getProperty("path.page.order");
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String addOrder(Model model, Locale locale,
                           @RequestParam(value = "startDate") String startDateString,
                           @RequestParam(value = "numberPlaces") int place,
                           @RequestParam(value = "endDate") String endDateString,
                           @RequestParam(value = "category") String category) {
        String page;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(startDateString, formatter);
            LocalDate endDate = LocalDate.parse(endDateString, formatter);
            if (startDate.isAfter(LocalDate.now().minusDays(1)) && startDate.isBefore(endDate)) {
                bookingService.addBooking(startDate, endDate, getUserIdByPrincipal(), place, category);
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

    @RequestMapping(value = "/bookings", method = RequestMethod.GET)
    public String myBookings(Model model, Locale locale) {
        String page = null;
        try {
            List<Booking> bookings = bookingService.getAllBookingByUser(getUserIdByPrincipal());
            model.addAttribute("bookingByUser", bookings);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", messageSource.getMessage("message.errorDatabase", null, locale));
        }
        return page;
    }

    @RequestMapping(value = "/unpaidaccounts", method = RequestMethod.GET)
    public String unpaidAccountsGet(Model model, Locale locale) {
        String page = null;
        try {
            int userId = getUserIdByPrincipal();
            List<Booking> bookings = bookingService.getAllBookingWithAccountByUser(userId);
            model.addAttribute("bookingByUser", bookings);
            List<Account> accounts = accountService.findAccountByUser(userId);
            model.addAttribute("accountById", accounts);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", messageSource.getMessage("message.errorDatabase", null, locale));
        }
        return page;
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public String myAccountsGet(Model model, Locale locale) {
        String page = null;
        try {
            int userId = getUserIdByPrincipal();
            List<Booking> bookings = bookingService.getAllBookingWithFinishedAccount(userId);
            model.addAttribute("bookingByUser", bookings);
            List<Account> accounts = accountService.findAccountByUser(userId);
            model.addAttribute("accountById", accounts);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", messageSource.getMessage("message.errorDatabase", null, locale));
        }
        return page;
    }

    @RequestMapping(value = "/unpaidaccounts", method = RequestMethod.GET, params = "id")
    public String payAccount(Model model, @RequestParam(value = "id") int bookingId, Locale locale) {
        String page;
        try {
            int userId = getUserIdByPrincipal();
            bookingService.payBooking(bookingId);
            List<Booking> bookings = bookingService.getAllBookingWithFinishedAccount(userId);
            model.addAttribute("bookingByUser", bookings);
            List<Account> accounts = accountService.findAccountByUser(userId);
            model.addAttribute("accountById", accounts);
            /*model.addAttribute("paySuccess", messageSource.getMessage("message.paySuccess", null, locale));*/
            page = ConfigurationManager.getProperty("path.page.myAccounts");
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", messageSource.getMessage("message.errorDatabase", null, locale));
        }
        return page;
    }

    private int getUserIdByPrincipal() throws ServiceException {
        int userId = 0;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String login = ((UserDetails) principal).getUsername();
            userId = userService.findByLogin(login).getUserId();
        }
        return userId;
    }
}