package com.hotel.service;

import com.hotel.entity.AbstractEntity;

/**
 * Created by Алексей on 01.10.2016.
 */
public abstract class AbstractService<T extends AbstractEntity> implements Service<T> {
    protected final String TRANSACTION_SUCCESS = "Transaction is completed successfully";
    protected final String TRANSACTION_FAIL = "Transaction failed. Error in service.";
}
