package com.hotel.command;

import com.hotel.command.client.OrderCommand;

public enum CommandEnum {
    ORDER {
        {
            this.command = new OrderCommand();
        }
    };
    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }

}
