package com.hotel.dao;

import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.AbstractEntity;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Алексей on 03.10.2016.
 */
public abstract class AbstractDAO<T extends AbstractEntity> implements DAO<T> {
    private final Logger LOG = Logger.getLogger(AbstractDAO.class);

    private Class pClass;

    @Autowired
    private SessionFactory sessionFactory;

    protected AbstractDAO(Class pClass, SessionFactory sessionFactory) {
        this.pClass = pClass;
        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(T entity) throws DaoException {
        try {
            Session session = getCurrentSession();
            session.saveOrUpdate(entity);
        } catch (HibernateException e) {
            LOG.error("Error in DAO " + e);
            throw new DaoException();
        }
    }

    @Override
    public void update(T entity) throws DaoException {

    }

    @Override
    public void delete(int id) throws DaoException {
        try {
            Session session = getCurrentSession();
            T account = (T) session.get(pClass, id);
            session.delete(account);
        } catch (HibernateException e) {
            LOG.error("Error in DAO " + e);
            throw new DaoException();
        }

    }

    @Override
    public List<T> getAll() throws DaoException {
        List<T> results;
        try {
            Session session = getCurrentSession();
            Criteria criteria = session.createCriteria(pClass);
            results = criteria.list();
        } catch (HibernateException e) {
            LOG.error("Error in DAO " + e);
            throw new DaoException();
        }
        return results;
    }

    @Override
    public T get(int id) throws DaoException {
        T entity;
        try {
            Session session = getCurrentSession();
            entity = (T) session.get(pClass, id);
        } catch (HibernateException e) {
            LOG.error("Error in DAO " + e);
            throw new DaoException();
        }
        return entity;
    }
}