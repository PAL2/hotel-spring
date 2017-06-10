package com.hotel.dao;

import com.hotel.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Алексей on 01.11.2016.
 */
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByStatus(String status);

    @Modifying
    @Query("UPDATE Booking B SET B.roomId=:roomId, B.status='billed' WHERE B.bookingId=:bookingId")
    void chooseRoom(@Param("bookingId") int bookingId, @Param("roomId") int roomId);

    List<Booking> findByUserId(int userId);

    @Modifying
    @Query("UPDATE Booking B SET B.status='rejected' WHERE B.bookingId=:bookingId")
    void rejectBooking(@Param("bookingId") int bookingId);

    List<Booking> findByAccountIdNot(int accountId);

    List<Booking> findByAccountIdNotAndStatusAndUserId(int accountId, String status, int userId);

    @Modifying
    @Query("UPDATE Booking B SET B.status='paid' WHERE B.bookingId=:bookingId")
    void payBooking(@Param("bookingId") int bookingId);

    List<Booking> findByAccountIdNotAndStatusAndUserIdOrStatus(int accountId, String paid, int userId, String refused);
}