package com.hotel.controllers;

import com.hotel.manager.ConfigurationManager;
import com.hotel.entity.User;
import com.hotel.service.UserService;
import com.hotel.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Locale;

/**
 * Created by Алексей on 11.11.2016.
 */

@org.springframework.stereotype.Controller
@SessionAttributes("user")
public class UserController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;

     @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        String page = ConfigurationManager.getProperty("path.page.login.redirect");
        return page;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        System.out.println("GET");
        String page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String showLoginPage(@ModelAttribute("newUser") User user,
                                ModelMap model, SessionStatus session, Locale locale) {
        String page;
        System.out.println("POST");
        try {
            try {
                user = userService.logIn(user.getLogin(), user.getPassword());
                model.addAttribute("user", user);
                if (user.getUserRole().equalsIgnoreCase("admin")) {
                    page = ConfigurationManager.getProperty("path.page.newBooking");
                } else {
                    page = ConfigurationManager.getProperty("path.page.order.redirect");
                }
            } catch (ServiceException e) {
                page = ConfigurationManager.getProperty("path.page.errorDatabase");
                model.addAttribute("errorDatabase", messageSource.getMessage("message.errorDatabase", null, locale));
            }
        } catch (NullPointerException e) {
            session.setComplete();
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

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String registration(@ModelAttribute("regUser") User user,
                               ModelMap model, SessionStatus session, Locale locale) {
        String page;
        try {
            model.addAttribute("regUser", user);
            System.out.println(user);
            userService.register(user.getFirstName(), user.getLastName(), user.getLogin(), user.getPassword());
            session.setComplete();
            model.addAttribute("regSuccess", messageSource.getMessage("message.regSuccess", null, locale));
            page = ConfigurationManager.getProperty("path.page.login.redirect");
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            model.addAttribute("errorDatabase", messageSource.getMessage("message.errorDatabase", null, locale));
        }
        return page;
    }

    /*@RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutUser(HttpServletRequest request,
                             HttpServletResponse response,
                             RedirectAttributes redirectAttributes) {
        System.out.println(redirectAttributes);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equalsIgnoreCase("anonymousUser")) {
            redirectAttributes.addFlashAttribute("errorLoginOrPassword", MessageManager.getProperty("message.loginError"));
        }
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login";
    }*/
}
