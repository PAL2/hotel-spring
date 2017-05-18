package com.hotel.entity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Алексей on 16.10.2016.
 */
@Entity
@Table(name = "booking", schema = "booking")
public class Booking extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "booking_id", nullable = false, unique = true)
    private Integer bookingId;

    @Basic
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Basic
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Basic
    @Column(name = "place", nullable = false)
    private int place;

    @Basic
    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @Basic
    @Column(name = "room_id", nullable = true, insertable = false, updatable = false)
    private Integer roomId;

    @Basic
    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    private int userId;

    @Basic
    @Column(name = "account_id", nullable = true, insertable = false, updatable = false)
    private Integer accountId;

    @Basic
    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true, referencedColumnName = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    public Booking() {
    }

    public Booking(LocalDate startDate, LocalDate endDate, int place, String category, Integer roomId, int userId,
                   Integer accountId, String status, Room room, User user, Account account) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.place = place;
        this.category = category;
        this.roomId = roomId;
        this.userId = userId;
        this.accountId = accountId;
        this.status = status;
        this.room = room;
        this.user = user;
        this.account = account;
    }


    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking that = (Booking) o;

        if (bookingId != that.bookingId) return false;
        if (place != that.place) return false;
        if (userId != that.userId) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (roomId != null ? !roomId.equals(that.roomId) : that.roomId != null) return false;
        if (accountId != null ? !accountId.equals(that.accountId) : that.accountId != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookingId;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + place;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (roomId != null ? roomId.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", place=" + place +
                ", category='" + category + '\'' +
                ", roomId=" + roomId +
                ", userId=" + userId +
                ", accountId=" + accountId +
                ", status='" + status + '\'' +
                '}';
    }
}