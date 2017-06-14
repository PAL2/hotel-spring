package com.hotel.service;

import com.hotel.entity.Booking;

import java.util.Date;
import java.util.List;

/**
 * Created by Алексей on 01.11.2016.
 */
public interface BookingService {
    List<Booking> getAllBookingWithAccount();

    void chooseRoom(int bookingId, int roomId);

    List<Booking> getAllNewBooking();

    void rejectBooking(int bookingId);

    List<Booking> getAllBookingByUser(int userId);

    void addBooking(Date startDate, Date endDate, int userId, int place, String category);

    void payBooking(int bookingId);

    List<Booking> getAllBookingWithAccountByUser(int userId);

    List<Booking> getAllBookingWithFinishedAccount(int userId);

    List<Booking> findAll();

    void delete(int bookingId);
}
