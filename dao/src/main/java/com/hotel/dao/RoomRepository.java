package com.hotel.dao;

import com.hotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Алексей on 01.11.2016.
 */
public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query(value = "(SELECT r.room_id, r.category, r.place, r.price FROM room AS r "
            + "LEFT JOIN booking AS b ON (b.room_id=r.room_id) LEFT JOIN (SELECT room_id FROM booking AS b WHERE "
            + "(b.start_date BETWEEN :startDate AND :endDate OR b.end_date BETWEEN :startDate AND :endDate)) AS v "
            + "ON (v.room_id=b.room_id) WHERE (r.category=:category) AND (r.place=:place) AND (b.room_id IS NULL))",
            nativeQuery = true)
    List<Room> findAvailableRooms(@Param("startDate") Date startDate, @Param("endDate") Date endDate,
                                  @Param("category") String category, @Param("place") int place);
}