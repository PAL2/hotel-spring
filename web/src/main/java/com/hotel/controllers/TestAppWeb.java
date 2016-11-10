package com.hotel.controllers;

import com.hotel.dao.BookingDAO;
import com.hotel.dao.exceptions.DaoException;
import com.hotel.service.BookingService;
import com.hotel.service.exceptions.ServiceException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Алексей on 10.11.2016.
 */
public class TestAppWeb {

    public static void main(String[] args) throws DaoException, ServiceException {

        ApplicationContext context = new ClassPathXmlApplicationContext("web-context.xml");
        BookingService bookingService = (BookingService) context.getBean("bookingServiceImpl");
        System.out.println("1 = " + bookingService);
        BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAOImpl");
        System.out.println("2 = " + bookingDAO);
        bookingService.getAllNewBooking();
        System.out.println("3 = " + bookingService.getAllNewBooking());
       // bookingDAO.getAllNewBooking();
        //System.out.println("4 = " + bookingDAO.getAllNewBooking());

    }

}
