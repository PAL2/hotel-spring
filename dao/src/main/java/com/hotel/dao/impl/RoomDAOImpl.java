package com.hotel.dao.impl;

import com.hotel.connect.DBUtil;
import com.hotel.dao.AbstractDAO;
import com.hotel.dao.RoomDAO;
import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.Booking;
import com.hotel.entity.Room;
import com.mysql.jdbc.PreparedStatement;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomDAOImpl extends AbstractDAO<Room> implements RoomDAO {
    private final Logger LOG = Logger.getLogger(RoomDAOImpl.class);

    @Autowired
    private RoomDAOImpl(SessionFactory sessionFactory) {
        super(Room.class, sessionFactory);
    }

    @Override
    public List<Room> resultSetToRoomsList(ResultSet resultSet) throws SQLException {
        List<Room> rooms = new ArrayList<>();
        while (resultSet.next()) {
            Room room = new Room();
            room.setRoomId(resultSet.getInt(1));
            room.setCategory(resultSet.getString(2));
            room.setPlace(resultSet.getInt(3));
            room.setPrice(resultSet.getInt(4));
            rooms.add(room);
        }
        return rooms;
    }


    //TO DO
    @Override
    public List<Room> getAvailableRooms(Booking booking) throws DaoException {
        Connection conn = DBUtil.getConnection();
        List<Room> rooms;
        Date startDate = (Date) booking.getStartDate();
        Date endDate = (Date) booking.getEndDate();
        try {
            String query = "(SELECT r.room_id, r.category, r.place, r.price FROM room AS r "
                    + "LEFT JOIN booking AS b ON (b.room_id=r.room_id) LEFT JOIN (SELECT room_id FROM booking AS b "
                    + "WHERE (b.start_date BETWEEN ? AND ? OR b.end_date BETWEEN ? AND ?)) AS v "
                    + "ON (v.room_id=b.room_id) WHERE (r.category=?) AND (r.place=?) AND (b.room_id IS NULL))";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setDate(1, startDate);
            ps.setDate(2, endDate);
            ps.setDate(3, startDate);
            ps.setDate(4, endDate);
            ps.setString(5, booking.getCategory());
            ps.setInt(6, booking.getPlace());
            ResultSet resultSet = ps.executeQuery();
            rooms = resultSetToRoomsList(resultSet);
            resultSet.close();
            ps.close();
        } catch (SQLException e) {
            LOG.error("Unable to create a list of matching numbers. Error in DAO. " + e);
            throw new DaoException("Unable to create a list of matching numbers. Error in DAO. " + e);
        }
        return rooms;
    }

    @Override
    public List<Room> getAll(int recordsPerPage, int currentPage) throws DaoException {
        List<Room> rooms;
        try {
            Session session = getCurrentSession();
            Query query = session.createQuery("from Room");
            query.setFirstResult((currentPage - 1) * recordsPerPage);
            query.setMaxResults(recordsPerPage);
            query.setCacheable(true);
            query.setCacheMode(CacheMode.NORMAL);
            rooms = query.list();
        } catch (HibernateException e) {
            LOG.error("Unable to return list of clients. Error in DAO. " + e);
            throw new DaoException("Unable to return list of clients. Error in DAO. " + e);
        }
        return rooms;
    }

    @Override
    public Long getAmount() throws DaoException {
        Long amount;
        try {
            Session session = getCurrentSession();
            Criteria criteria = session.createCriteria(Room.class);
            criteria.setProjection(Projections.rowCount());
            criteria.setCacheable(true);
            amount = (Long) criteria.uniqueResult();
        } catch (HibernateException e) {
            LOG.error("Unable to get number of records. Error in DAO. " + e);
            throw new DaoException("Unable to get number of records. Error in DAO. " + e);
        }
        return amount;
    }
}