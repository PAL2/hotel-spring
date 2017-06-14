package com.hotel.service;

import com.hotel.entity.Account;

import java.util.List;

/**
 * Created by Алексей on 01.11.2016.
 */
public interface AccountService {

    List<Account> findAccountByUser(int userId);

    List<Account> findAll();
}