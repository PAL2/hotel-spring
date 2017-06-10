package com.hotel.service.impl;

import com.hotel.dao.BookingRepository;
import com.hotel.dao.RoomRepository;
import com.hotel.entity.Booking;
import com.hotel.entity.Room;
import com.hotel.service.RoomService;
import com.hotel.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Алексей on 01.10.2016.
 */

@Service
@Repository
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<Room> getAvailableRooms(int bookingId) throws ServiceException {
        try {
            Booking booking = bookingRepository.findOne(bookingId);
            return roomRepository.findAvailableRooms(booking.getStartDate(), booking.getEndDate(),
                    booking.getCategory(), booking.getPlace());
        } catch (Exception e) {
            throw new ServiceException("", e);
        }
    }

    @Override
    public Page<Room> findAll(Integer pageNumber) throws ServiceException {
        try {
            PageRequest request = new PageRequest(pageNumber - 1, 10);
            return roomRepository.findAll(request);
        } catch (Exception e) {
            throw new ServiceException("", e);
        }
    }
}