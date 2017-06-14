package com.hotel.service.impl;

import com.google.common.collect.Lists;
import com.hotel.dao.AccountRepository;
import com.hotel.entity.Account;
import com.hotel.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Алексей on 02.10.2016.
 */

@Service
@Repository
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<Account> findAll() {
        return Lists.newArrayList(accountRepository.findAll());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<Account> findAccountByUser(int userId) {
        return accountRepository.findAccountByUser(userId);
    }
}