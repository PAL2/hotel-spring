package com.hotel.entity;

import javax.persistence.*;

/**
 * Created by Алексей on 16.10.2016.
 */
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false, unique = true)
    private Integer accountId;

    @Column(name = "summa", nullable = false)
    private int sum;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "account")
    private Booking booking;

    public Account() {
    }

    public Account(int sum) {
        this.sum = sum;
    }

    public Account(int sum, Booking booking) {
        this.sum = sum;
        this.booking = booking;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account that = (Account) o;

        if (accountId != that.accountId) return false;
        if (sum != that.sum) return false;

        return true;
    }

    @Override
    public int hashCode() {
        Integer result = accountId;
        result = 31 * result + sum;
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", sum=" + sum +
                '}';
    }
}