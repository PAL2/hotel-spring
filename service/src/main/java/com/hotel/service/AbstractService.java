package com.hotel.service;

import com.hotel.dao.AbstractDAO;
import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.AbstractEntity;
import com.hotel.service.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Алексей on 01.10.2016.
 */
public abstract class AbstractService<T extends AbstractEntity> implements Service<T> {
    protected final String TRANSACTION_SUCCESS = "Transaction is completed successfully";
    protected final String TRANSACTION_FAIL = "Transaction failed. Error in service.";

    private final Logger LOG = Logger.getLogger(AccountService.class);

    AbstractDAO<T> abstractDAO;

    @Override
    @Transactional
    public T get(int id) throws ServiceException {
        T entity;
        try {
            entity = abstractDAO.get(id);
            LOG.info(entity);
            LOG.info(TRANSACTION_SUCCESS);
        } catch (DaoException e) {
            LOG.error(TRANSACTION_FAIL, e);
            throw new ServiceException(TRANSACTION_FAIL, e);
        }
        return entity;
    }
}
