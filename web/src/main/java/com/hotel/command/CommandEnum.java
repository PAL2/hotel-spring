package com.hotel.command;

import com.hotel.command.admin.*;
import com.hotel.command.client.*;
import com.hotel.command.user.LogoutCommand;
import com.hotel.command.user.RegCommand;

public enum CommandEnum {
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    ORDER {
        {
            this.command = new OrderCommand();
        }
    },
    BILL {
        {
            this.command = new BillCommand();
        }
    },
    MYACCOUNTS {
        {
            this.command = new MyAccountsCommand();
        }
    },
    REG {
        {
            this.command = new RegCommand();
        }
    },
    NEWBOOKING {
        {
            this.command = new NewBookingCommand();
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
