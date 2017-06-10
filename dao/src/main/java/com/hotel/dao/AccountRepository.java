package com.hotel.dao;

import com.hotel.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Алексей on 09.06.2017.
 */
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("FROM Account WHERE booking.userId=:userId")
    List<Account> findAccountByUser(@Param("userId") int userId);
}