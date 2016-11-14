package com.hotel.command;

import com.hotel.command.client.*;

public enum CommandEnum {
    ORDER {
        {
            this.command = new OrderCommand();
        }
    },
    MYACCOUNTS {
        {
            this.command = new MyAccountsCommand();
        }
    },
    MYBOOKING {
        {
            this.command = new MyBookingCommand();
        }
    },
    UNPAIDACCOUNT {
        {
            this.command = new UnpaidAccountCommand();
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
