package com.hotel.dao;

import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.AbstractEntity;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Алексей on 01.11.2016.
 */
public interface DAO<T extends AbstractEntity> {

    void save(T entity) throws DaoException;

    void delete(int id) throws DaoException;

    List<T> getAll() throws DaoException;

    T get(int id) throws DaoException;

    BigInteger getLastGeneratedValue() throws DaoException;
}