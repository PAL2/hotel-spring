package com.hotel.controllers;

import com.hotel.command.ConfigurationManager;
import com.hotel.command.MessageManager;
import com.hotel.entity.User;
import com.hotel.service.BookingService;
import com.hotel.service.UserService;
import com.hotel.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 * Created by Алексей on 11.11.2016.
 */

@org.springframework.stereotype.Controller
@SessionAttributes("user")
public class UserController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        String page = ConfigurationManager.getProperty("path.page.login.redirect");
        return page;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(Model model) {
        String page = ConfigurationManager.getProperty("path.page.login");
        User user = new User();
        model.addAttribute("newUser", user);
        return page;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String showLoginPage(@ModelAttribute("newUser") User user, ModelMap model, SessionStatus session) {
        String page;
        try {
            try {
                user = userService.logIn(user.getLogin(), user.getPassword());
                model.addAttribute("user", user);
                if (user.getUserRole().equalsIgnoreCase("admin")) {
                    page = ConfigurationManager.getProperty("path.page.newBooking");
                } else {
                    page = ConfigurationManager.getProperty("path.page.order");
                }
            } catch (ServiceException e) {
                page = ConfigurationManager.getProperty("path.page.errorDatabase");
                model.addAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
            }
        } catch (NullPointerException e) {
            session.setComplete();
            model.addAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginError"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logOut(SessionStatus session) {
        String page = ConfigurationManager.getProperty("path.page.login.redirect");
        session.setComplete();
        return page;
    }

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String logOut(Model model) {
        User user = new User();
        model.addAttribute("regUser", user);
        String page = ConfigurationManager.getProperty("path.page.reg");
        return page;
    }
}
