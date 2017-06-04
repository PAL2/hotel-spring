package com.hotel.controllers;

import com.hotel.entity.Account;
import com.hotel.entity.Booking;
import com.hotel.entity.Room;
import com.hotel.entity.User;
import com.hotel.manager.ConfigurationManager;
import com.hotel.service.AccountService;
import com.hotel.service.BookingService;
import com.hotel.service.RoomService;
import com.hotel.service.UserService;
import com.hotel.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;

/**
 * Created by Алексей on 13.11.2016.
 */

@Controller
@RequestMapping("/admin")
@ManagedResource(objectName = "bean:name=adminController")
public class AdminController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return ConfigurationManager.getProperty("path.page.newBooking.reject");
    }

    @ManagedOperation(description = "new booking GET")
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "model", description = "Model"),
            @ManagedOperationParameter(name = "locale", description = "Locale")
    })
    @RequestMapping(value = "/newbooking", method = RequestMethod.GET)
    public String newBookingGet(Model model, Locale locale) throws ServiceException {
        String page = null;
        try {
            List<Booking> bookings = bookingService.getAllNewBooking();
            model.addAttribute("newBooking", bookings);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", messageSource.getMessage("message.errorDatabase", null, locale));
        }
        return page;
    }

    @ManagedOperation(description = "All booking GET")
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "model", description = "Model"),
            @ManagedOperationParameter(name = "locale", description = "Locale")
    })
    @RequestMapping(value = "/allbookings", method = RequestMethod.GET)
    public String allBookingGet(Model model, Locale locale) {
        String page = null;
        try {
            List<Booking> bookings = bookingService.getAll();
            model.addAttribute("allBooking", bookings);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", messageSource.getMessage("message.errorDatabase", null, locale));
        }
        return page;
    }

    @ManagedOperation
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "model", description = "Model"),
            @ManagedOperationParameter(name = "bookingId", description = "booking ID"),
            @ManagedOperationParameter(name = "locale", description = "Locale")
    })
    @RequestMapping(value = "/allbookings", method = RequestMethod.GET, params = "id")
    public String deleteBooking(Model model, @RequestParam(value = "id") int bookingId, Locale locale) {
        String page;
        try {
            page = ConfigurationManager.getProperty("path.page.allBookings.delete");
            bookingService.delete(bookingId);
            List<Booking> bookings = bookingService.getAll();
            model.addAttribute("allBooking", bookings);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", messageSource.getMessage("message.errorDatabase", null, locale));
        }
        return page;
    }

    @ManagedOperation
    @ManagedOperationParameter(name = "model", description = "model")
    @RequestMapping(value = "/allaccounts", method = RequestMethod.GET)
    public String allAccountsGet(Model model/*, Locale locale*/) {
        String page = null;
        try {
            List<Booking> bookings = bookingService.getAllBookingWithAccount();
            model.addAttribute("allBookings", bookings);
            List<Account> accounts = accountService.getAll();
            model.addAttribute("allAccounts", accounts);
            List<User> users = userService.getAll();
            model.addAttribute("allUsers", users);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            /*model.addAttribute("errorDatabase", messageSource.getMessage("message.errorDatabase", null, locale));*/
        }
        return page;
    }

    @ManagedOperation
    @ManagedOperationParameter(name = "model", description = "model")
    @RequestMapping(value = "/allusers", method = RequestMethod.GET)
    public String allUsersGet(Model model/*, Locale locale*/) {
        String page = null;
        try {
            List<User> users = userService.getAll();
            model.addAttribute("allUsers", users);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            /*model.addAttribute("errorDatabase", messageSource.getMessage("message.errorDatabase", null, locale));*/
        }
        return page;
    }

    @ManagedOperation(description = "All rooms GET")
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "model", description = "Model"),
            @ManagedOperationParameter(name = "locale", description = "Locale")
    })
    @RequestMapping(value = "/allrooms", method = RequestMethod.GET)
    public String allRooms(Model model, Locale locale) {
        String page = null;
        int recordsPerPage = 10;
        int currentPage = 1;
        try {
            int numberOfPages = roomService.getNumberOfPages(recordsPerPage);
            List<Room> rooms = roomService.getAll(recordsPerPage, currentPage);
            model.addAttribute("allRooms", rooms);
            model.addAttribute("numberOfPages", numberOfPages);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", messageSource.getMessage("message.errorDatabase", null, locale));
        }
        return page;
    }

    @ManagedOperation
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "model", description = "Model"),
            @ManagedOperationParameter(name = "currentPage", description = "CurrentPage"),
            @ManagedOperationParameter(name = "locale", description = "Locale")
    })
    @RequestMapping(value = "/allrooms", method = RequestMethod.GET, params = "currentPage")
    public String allRoomsPag(Model model, @ModelAttribute("currentPage") int currentPage, Locale locale) {
        String page = null;
        int recordsPerPage = 10;
        try {
            int numberOfPages = roomService.getNumberOfPages(recordsPerPage);
            List<Room> rooms = roomService.getAll(recordsPerPage, currentPage);
            model.addAttribute("allRooms", rooms);
            model.addAttribute("numberOfPages", numberOfPages);
            model.addAttribute("currentPage", currentPage);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", messageSource.getMessage("message.errorDatabase", null, locale));
        }
        return page;
    }

    @ManagedOperation
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "model", description = "Model"),
            @ManagedOperationParameter(name = "bookingId", description = "Booking Id"),
            @ManagedOperationParameter(name = "locale", description = "Locale")
    })
    @RequestMapping(value = "/newbooking", method = RequestMethod.GET, params = "id")
    public String rejectBooking(Model model, @RequestParam(value = "id") int bookingId, Locale locale) {
        String page;
        try {
            page = ConfigurationManager.getProperty("path.page.newBooking.reject");
            bookingService.rejectBooking(bookingId);
            List<Booking> bookings = bookingService.getAllNewBooking();
            model.addAttribute("newBooking", bookings);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", messageSource.getMessage("message.errorDatabase", null, locale));
        }
        return page;
    }

    @ManagedOperation
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "model", description = "Model"),
            @ManagedOperationParameter(name = "bookingId", description = "Booking Id"),
            @ManagedOperationParameter(name = "locale", description = "Locale")
    })
    @RequestMapping(value = "/chooseroom", method = RequestMethod.GET, params = "id")
    public String chooseRoom(Model model, @RequestParam(value = "id") int bookingId, Locale locale) {
        String page;
        try {
            page = ConfigurationManager.getProperty("path.page.chooseRoom");
            List<Room> rooms = roomService.getAvailableRooms(bookingId);
            model.addAttribute("availableRooms", rooms);
            model.addAttribute("id", bookingId);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", messageSource.getMessage("message.errorDatabase", null, locale));
        }
        return page;
    }

    @ManagedOperation
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "model", description = "Model"),
            @ManagedOperationParameter(name = "locale", description = "Locale")
    })
    @RequestMapping(value = "/chooseroom", method = RequestMethod.GET)
    public String chooseRoom(Model model, Locale locale) {
        String page;
        try {
            page = ConfigurationManager.getProperty("path.page.chooseRoom");
        } catch (Exception e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", messageSource.getMessage("message.errorDatabase", null, locale));
        }
        return page;
    }

    @ManagedOperation
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "model", description = "Model"),
            @ManagedOperationParameter(name = "bookingId", description = "Booking Id"),
            @ManagedOperationParameter(name = "roomId", description = "Room Id"),
            @ManagedOperationParameter(name = "locale", description = "Locale")
    })
    @RequestMapping(value = "/chooseroom", method = RequestMethod.GET, params = {"id", "room"})
    public String chooseRoom(Model model, @RequestParam(value = "id") int bookingId,
                             @RequestParam(value = "room") int roomId, Locale locale) {
        String page;
        try {
            page = ConfigurationManager.getProperty("path.page.newBooking.bill");
            bookingService.chooseRoom(bookingId, roomId);
            List<Booking> bookings = bookingService.getAllNewBooking();
            model.addAttribute("newBooking", bookings);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", messageSource.getMessage("message.errorDatabase", null, locale));
        }
        return page;
    }
}