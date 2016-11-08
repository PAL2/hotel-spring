package com.hotel.command.admin;

import com.hotel.command.ActionCommand;
import com.hotel.command.ConfigurationManager;
import com.hotel.command.MessageManager;
import com.hotel.entity.Room;
import com.hotel.service.RoomService;
import com.hotel.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ChooseRoomCommand implements ActionCommand {

    @Autowired
    private RoomService roomService;

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        int bookingId = Integer.parseInt(request.getParameter("booking_id"));
        request.getSession().setAttribute("booking_id", bookingId);
        try {
            page = ConfigurationManager.getProperty("path.page.chooseRoom");
            List<Room> rooms = roomService.getAvailableRooms(bookingId);
            request.setAttribute("availableRooms", rooms);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty("path.page.errorDatabase");
            request.setAttribute("errorDatabase", MessageManager.getProperty("message.errorDatabase"));
        }
        return page;
    }

}
