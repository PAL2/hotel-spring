package com.hotel.service;

import com.hotel.entity.Booking;
import com.hotel.service.exceptions.ServiceException;

import java.util.Date;
import java.util.List;

/**
 * Created by Алексей on 01.11.2016.
 */
public interface BookingService {
    List<Booking> getAllBookingWithAccount() throws ServiceException;

    void chooseRoom(int bookingId, int roomId) throws ServiceException;

    List<Booking> getAllNewBooking() throws ServiceException;

    void rejectBooking(int bookingId) throws ServiceException;

    List<Booking> getAllBookingByUser(int userId) throws ServiceException;

    void addBooking(Date startDate, Date endDate, int userId, int place, String category)
            throws ServiceException;

    void payBooking(int bookingId) throws ServiceException;

    List<Booking> getAllBookingWithAccountByUser(int userId) throws ServiceException;

    List<Booking> getAllBookingWithFinishedAccount(int userId) throws ServiceException;

    List<Booking> findAll() throws ServiceException;

    void delete(int bookingId) throws ServiceException;
}
