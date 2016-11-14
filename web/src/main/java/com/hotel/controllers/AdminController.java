package com.hotel.controllers;

import com.hotel.command.ConfigurationManager;
import com.hotel.command.MessageManager;
import com.hotel.entity.Account;
import com.hotel.entity.Booking;
import com.hotel.entity.Room;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Алексей on 13.11.2016.
 */

@org.springframework.stereotype.Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/newbooking", method = RequestMethod.GET)
    public String newBookingGet(Model model) throws ServiceException {
        String page;
        try {
            page = null;
            List<Booking> bookings = bookingService.getAllNewBooking();
            model.addAttribute("newBooking", bookings);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

    @RequestMapping(value = "/newbooking", method = RequestMethod.POST)
    public String newBookingPost(Model model) throws ServiceException {
        String page;
        try {
            List<Booking> bookings = bookingService.getAllNewBooking();
            model.addAttribute("newBooking", bookings);
            page = null;
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

    @RequestMapping(value = "/allbookings", method = RequestMethod.POST)
    public String allBooking(Model model) {
        String page;
        try {
            page = null;
            List<Booking> bookings = bookingService.getAll();
            model.addAttribute("allBooking", bookings);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

    @RequestMapping(value = "/allbookings", method = RequestMethod.GET)
    public String allBookingGet(Model model) {
        String page;
        try {
            page = null;
            List<Booking> bookings = bookingService.getAll();
            model.addAttribute("allBooking", bookings);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

    @RequestMapping(value = "/allbookings", method = RequestMethod.POST, params = "id")
    public String deleteBooking(Model model, @RequestParam(value = "id") int bookingId) {
        String page;
        try {
            page = ConfigurationManager.getProperty("path.page.allBookings.delete");
            bookingService.delete(bookingId);
            List<Booking> bookings = bookingService.getAll();
            model.addAttribute("allBooking", bookings);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

    @RequestMapping(value = "/allaccounts", method = RequestMethod.POST)
    public String allAccounts(Model model) {
        String page;
        try {
            page = null;
            List<Booking> bookings = bookingService.getAllBookingWithAccount();
            model.addAttribute("allBookings", bookings);
            List<Account> accounts = accountService.getAll();
            model.addAttribute("allAccounts", accounts);
            List<User> users = userService.getAll();
            model.addAttribute("allUsers", users);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

    @RequestMapping(value = "/allusers", method = RequestMethod.POST)
    public String allUsers(Model model) {
        String page;
        try {
            page = null;
            List<User> users = userService.getAll();
            model.addAttribute("allUsers", users);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

    @RequestMapping(value = "/allrooms", method = RequestMethod.POST)
    public String allRooms(Model model) {
        String page;
        int recordsPerPage = 10;
        int currentPage = 1;
        try {
            page = null;
            int numberOfPages = roomService.getNumberOfPages(recordsPerPage);
            List<Room> rooms = roomService.getAll(recordsPerPage, currentPage);
            model.addAttribute("allRooms", rooms);
            model.addAttribute("numberOfPages", numberOfPages);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

    @RequestMapping(value = "/allrooms", method = RequestMethod.GET, params = "currentPage")
    public String allRoomsPag(Model model, @ModelAttribute("currentPage") int currentPage) {
        String page;
        int recordsPerPage = 10;
        try {
            page = null;
            int numberOfPages = roomService.getNumberOfPages(recordsPerPage);
            List<Room> rooms = roomService.getAll(recordsPerPage, currentPage);
            model.addAttribute("allRooms", rooms);
            model.addAttribute("numberOfPages", numberOfPages);
            model.addAttribute("currentPage", currentPage);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

    @RequestMapping(value = "/newbooking", method = RequestMethod.POST, params = "id")
    public String rejectBooking(Model model, @RequestParam(value = "id") int bookingId) {
        String page;
        try {
            System.out.println("reject");
            page = ConfigurationManager.getProperty("path.page.newBooking.reject");
            bookingService.rejectBooking(bookingId);
            List<Booking> bookings = bookingService.getAllNewBooking();
            model.addAttribute("newBooking", bookings);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

    @RequestMapping(value = "/chooseroom", method = RequestMethod.POST, params = "id")
    public String chooseRoom(Model model, @RequestParam(value = "id") int bookingId) {
        String page;
        try {
            System.out.println("choose");
            page = ConfigurationManager.getProperty("path.page.chooseRoom");
            List<Room> rooms = roomService.getAvailableRooms(bookingId);
            model.addAttribute("availableRooms", rooms);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }
}