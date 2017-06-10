package com.hotel.service;

import com.hotel.entity.Account;
import com.hotel.service.exceptions.ServiceException;

import java.util.List;

/**
 * Created by Алексей on 01.11.2016.
 */
public interface AccountService {

    List<Account> findAccountByUser(int userId) throws ServiceException;

    List<Account> findAll() throws ServiceException;
}