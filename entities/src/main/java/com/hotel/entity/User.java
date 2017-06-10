package com.hotel.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Алексей on 16.10.2016.
 */
@Entity
public class User extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @NotEmpty(message = "{validation.firstName.NotEmpty.message}")
    @Size(min = 2, max = 30, message = "{validation.firstName.Size.message}")
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @NotEmpty(message = "{validation.lastName.NotEmpty.message}")
    @Size(min = 2, max = 30, message = "{validation.lastName.Size.message}")
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Column(name = "user_role", nullable = false, length = 50)
    private String userRole;

    @NotEmpty(message = "{validation.login.NotEmpty.message}")
    @Size(min = 2, max = 30, message = "{validation.login.Size.message}")
    @Column(name = "login", nullable = false, length = 20)
    private String login;

    @NotEmpty(message = "{validation.password.NotEmpty.message}")
    @Size(min = 2, max = 40, message = "{validation.password.Size.message}")
    @Column(name = "password", nullable = false, length = 355)
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Booking> bookingEntities;

    public User() {
    }

    public User(String firstName, String lastName, String userRole, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = userRole;
        this.login = login;
        this.password = password;
    }

    public User(String firstName, String lastName, String userRole, String login, String password,
                List<Booking> bookingEntities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = userRole;
        this.login = login;
        this.password = password;
        this.bookingEntities = bookingEntities;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Booking> getBookingsEntities() {
        return bookingEntities;
    }

    public void setBookingsEntities(List<Booking> bookingEntities) {
        this.bookingEntities = bookingEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (userRole != null ? !userRole.equals(user.userRole) : user.userRole != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        return password != null ? password.equals(user.password) : user.password == null;

    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userRole='" + userRole + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}