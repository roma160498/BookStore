package Controller;

import java.util.HashMap;
import java.util.Map;

import Controller.command.Command;
import Controller.command.implementation.UserLogCommand;
import Controller.command.implementation.UserRegCommand;

class CommandProvider {
	private Map<String, Command> commands = new HashMap<>();

	CommandProvider() {
		commands.put("user_reg", new UserRegCommand());
		commands.put("user_log", new UserLogCommand());
	}

	Command getCommand(String commandName) {
		Command command;
		command = commands.get(commandName);
		return command;

	}

}