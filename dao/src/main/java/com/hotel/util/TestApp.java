package com.hotel.util;

import com.hotel.dao.BookingDAO;
import com.hotel.dao.exceptions.DaoException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Алексей on 16.10.2016.
 */
public class TestApp {

    public static void main(String[] args) throws DaoException {

        ApplicationContext context = new ClassPathXmlApplicationContext("dao-context.xml");
        BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAOImpl");
        System.out.println(bookingDAO);
        //bookingDAO.getAllNewBooking();
       // System.out.println(bookingDAO.getAllNewBooking());

    }
}