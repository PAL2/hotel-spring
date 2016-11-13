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
 * Created by Алексей on 10.11.2016.
 */

@org.springframework.stereotype.Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;



}
