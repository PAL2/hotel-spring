package com.hotel.controllers;

import com.hotel.command.ConfigurationManager;
import com.hotel.command.MessageManager;
import com.hotel.entity.Booking;
import com.hotel.entity.User;
import com.hotel.service.BookingService;
import com.hotel.service.UserService;
import com.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Алексей on 10.11.2016.
 */

@org.springframework.stereotype.Controller
@RequestMapping ("/user")
public class UserController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String qqq (Model model){
        System.out.println("get");
        model.addAttribute("user", new User());
        return "user/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String showLoginPage(@ModelAttribute("user") User user) {
        String page;
        final Logger LOG = Logger.getLogger(UserController.class);
        //String login = request.getParameter("login");
        //String password = request.getParameter("password");
        try {
            System.out.println(userService);
            //User user = userService.logIn(login, password);
            user = userService.logIn("admin", "admin");
            System.out.println(userService);
           // request.getSession().setAttribute("user", user);
            //LOG.info("On the site came user with login : " + login);
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
