package com.hotel.service.testApp;

import com.hotel.dao.BookingDAO;
import com.hotel.dao.exceptions.DaoException;
import com.hotel.service.BookingService;
import com.hotel.service.exceptions.ServiceException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Алексей on 10.11.2016.
 */
public class TestAppService {
    public static void main(String[] args) throws DaoException, ServiceException {

        ApplicationContext context = new ClassPathXmlApplicationContext("service-context.xml");
        BookingService bookingService = (BookingService) context.getBean("bookingServiceImpl");
        System.out.println(bookingService);
        BookingDAO bookingDAO = (BookingDAO) context.getBean("bookingDAOImpl");
        System.out.println(bookingDAO);
        bookingService.getAllNewBooking();
        System.out.println(bookingService.getAllNewBooking());
        //bookingDAO.getAllNewBooking();
        //System.out.println(bookingDAO.getAllNewBooking());

    }
}
