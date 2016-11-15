package com.hotel.command;

import com.hotel.command.client.OrderCommand;
import com.hotel.command.client.PayCommand;

public enum CommandEnum {
    ORDER {
        {
            this.command = new OrderCommand();
        }
    },
    PAY {
        {
            this.command = new PayCommand();
        }
    };
    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }

}
