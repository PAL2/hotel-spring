package com.hotel.service;

import com.hotel.entity.Room;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Алексей on 01.11.2016.
 */
public interface RoomService {
    List<Room> getAvailableRooms(int bookingId);

    Page<Room> findAll(int pageNumber);
}
