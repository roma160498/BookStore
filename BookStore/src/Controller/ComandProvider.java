package Controller;

import java.util.HashMap;
import java.util.Map;

import Controller.command.Command;
import Controller.command.implementation.AddBookCommand;
import Controller.command.implementation.DelBookCommand;
import Controller.command.implementation.SearchBooksCommand;
import Controller.command.implementation.ShowBooksCommand;
import Controller.command.implementation.UserLogCommand;
import Controller.command.implementation.UserRegCommand;

class CommandProvider {
	private Map<String, Command> commands = new HashMap<>();

	CommandProvider() {
		commands.put("user_reg", new UserRegCommand());
		commands.put("user_log", new UserLogCommand());
		commands.put("add_book", new AddBookCommand());
		commands.put("del_book", new DelBookCommand());
		commands.put("show_books", new ShowBooksCommand());
		commands.put("search_books", new SearchBooksCommand());
	}

	Command getCommand(String commandName) {
		Command command;
		command = commands.get(commandName);
		return command;

	}

}