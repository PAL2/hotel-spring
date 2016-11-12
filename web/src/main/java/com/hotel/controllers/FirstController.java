package com.hotel.controllers;

import com.hotel.entity.User;
import com.hotel.service.BookingService;
import com.hotel.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Алексей on 11.11.2016.
 */

@org.springframework.stereotype.Controller
public class FirstController {

    @Autowired
    private BookingService bookingService;


    @RequestMapping (value = "/index", method = RequestMethod.GET)
    public String index (){
        System.out.println("index");

        return "redirect:/user";
    }

    @RequestMapping (value = "/user", method = RequestMethod.GET)
       public String login(Model model) throws ServiceException {
        System.out.println("method GET");
        System.out.println(new User().toString());
        User user = new User();
        user.setUserRole("client");
        System.out.println(model.toString());
        model.addAttribute("user", user);
        System.out.println(model.toString());
        //return "redirect:user/login";
        return "user/login";
    }

    @RequestMapping (value = "/user", method = RequestMethod.POST)
    public ModelAndView main2(Model model) {

        System.out.println("POST");
        System.out.println(new User().toString());
        User user = new User();
        user.setUserRole("client");
        System.out.println(model.toString());
        model.addAttribute("user", user);
        System.out.println(model.toString());
        return new ModelAndView("user/login", "user", new User());
    }

}
