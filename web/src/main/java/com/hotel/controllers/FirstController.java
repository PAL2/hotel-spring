package com.hotel.controllers;

import com.hotel.command.ConfigurationManager;
import com.hotel.entity.Booking;
import com.hotel.entity.User;
import com.hotel.service.BookingService;
import com.hotel.service.UserService;
import com.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Алексей on 11.11.2016.
 */

@org.springframework.stereotype.Controller
public class FirstController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String qqq(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);
        System.out.println(user);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String showLoginPage(@ModelAttribute("user") User user, @ModelAttribute("newUser") User user2, Model model)
            /*@RequestParam (value = "login", required = true) String login,
                                @RequestParam (value = "password", required = true) String password)*/ {
        String page;
        System.out.println(user2);
        System.out.println(user);
        final Logger LOG = Logger.getLogger(UserController.class);
        try {
            user = userService.logIn(user.getLogin(), user.getPassword());
            System.out.println(user);
            // request.getSession().setAttribute("user", user);
            try {
                if (user.getUserRole().equalsIgnoreCase("admin")) {
                    page = ConfigurationManager.getProperty("path.page.admin");
                    //request.getSession().setAttribute("isAdmin", true);
                    List<Booking> bookings = bookingService.getAllNewBooking();
                    // request.setAttribute("newBooking", bookings);
                } else {
                    page = ConfigurationManager.getProperty("path.page.order");
                }
            } catch (ServiceException e) {
                page = ConfigurationManager.getProperty("path.page.errorDatabase");
                //  request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
            }
        } catch (NullPointerException e) {
            // request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginError"));
            page = ConfigurationManager.getProperty("path.page.login");
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            //  request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

}
