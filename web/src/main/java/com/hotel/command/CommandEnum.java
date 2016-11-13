package com.hotel.command;

import com.hotel.command.user.LogoutCommand;
import com.hotel.command.user.RegCommand;
import com.hotel.command.admin.*;
import com.hotel.command.client.*;

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
	CHOOSEROOM {
		{
			this.command = new ChooseRoomCommand();
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
	REJECT {
		{
			this.command = new RejectCommand();
		}
	},
	NEWBOOKING {
		{
			this.command = new NewBookingCommand();
		}
	},
	DELETEBOOKING {
		{
			this.command = new DeleteBookingCommand();
		}
	},
	ALLROOM {
		{
			this.command = new AllRoomCommand();
		}
	},
	MYBOOKING {
		{
			this.command = new MyBookingCommand();
		}
	},
	UNPAIDACCOUNT{
		{
			this.command = new UnpaidAccountCommand();
		}
	},
	PAY{
		{
			this.command = new PayCommand();
		}
	},
	REFUSE{
		{
			this.command = new RefuseCommand();
		}
	},
	GOORDER{
		{
			this.command = new GoOrderCommand();
		}
	};
	ActionCommand command;

	public ActionCommand getCurrentCommand() {
		return command;
	}

}
