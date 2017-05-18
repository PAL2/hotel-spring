package com.hotel.controllers;

import com.hotel.entity.User;
import com.hotel.manager.ConfigurationManager;
import com.hotel.service.UserService;
import com.hotel.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by Алексей on 11.11.2016.
 */

@org.springframework.stereotype.Controller
public class UserController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return ConfigurationManager.getProperty("path.page.login.redirect");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return ConfigurationManager.getProperty("path.page.login");
    }

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String logOut(Model model) {
        User user = new User();
        model.addAttribute("regUser", user);
        return ConfigurationManager.getProperty("path.page.reg");
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String registration(@ModelAttribute("regUser") User user,
                               ModelMap model, SessionStatus session, Locale locale) {
        String page;
        try {
            model.addAttribute("regUser", user);
            userService.register(user.getFirstName(), user.getLastName(), user.getLogin(), user.getPassword());
            session.setComplete();
            // model.addAttribute("regSuccess", messageSource.getMessage("message.regSuccess", null, locale));
            page = ConfigurationManager.getProperty("path.page.login.redirect");
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", messageSource.getMessage("message.errorDatabase", null, locale));
        }
        return page;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage() {
        return "accessDenied";
    }
}