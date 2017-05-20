package com.hotel.dao.impl;

import com.hotel.dao.AbstractDAO;
import com.hotel.dao.RoomDAO;
import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.Booking;
import com.hotel.entity.Room;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    @Override
    public List<Room> getAvailableRooms(Booking booking) throws DaoException {
        List<Room> rooms;
        String sqlQuery = "(SELECT r.room_id, r.category, r.place, r.price FROM room AS r "
                + "LEFT JOIN booking AS b ON (b.room_id=r.room_id) LEFT JOIN (SELECT room_id FROM booking AS b WHERE "
                + "(b.start_date BETWEEN :startDate AND :endDate OR b.end_date BETWEEN :startDate AND :endDate)) AS v "
                + "ON (v.room_id=b.room_id) WHERE (r.category=:category) AND (r.place=:place) AND (b.room_id IS NULL))";
        try {
            Session session = getCurrentSession();
            SQLQuery query = session.createSQLQuery(sqlQuery);
            query.addEntity(Room.class);
            query.setParameter("startDate", booking.getStartDate());
            query.setParameter("endDate", booking.getEndDate());
            query.setParameter("category", booking.getCategory());
            query.setParameter("place", booking.getPlace());
            rooms = query.list();
        } catch (HibernateException e) {
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