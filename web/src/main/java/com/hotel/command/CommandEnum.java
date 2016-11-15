package com.hotel.command;

import com.hotel.command.client.GoOrderCommand;
import com.hotel.command.client.OrderCommand;
import com.hotel.command.client.PayCommand;
import com.hotel.command.client.RefuseCommand;

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
    },
    REFUSE {
        {
            this.command = new RefuseCommand();
        }
    },
    GOORDER {
        {
            this.command = new GoOrderCommand();
        }
    };
    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }

}
