package com.hotel.service;

import com.hotel.entity.Room;
import com.hotel.service.exceptions.ServiceException;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Алексей on 01.11.2016.
 */
public interface RoomService {
    List<Room> getAvailableRooms(int bookingId) throws ServiceException;

    Page<Room> findAll(Integer pageNumber) throws ServiceException;
}
