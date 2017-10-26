package Controller;

import Controller.command.Command;

public class Controller {

	private final CommandProvider provider = new CommandProvider();	
	
	public String doAction(String request) {
		String commandName;
		commandName = request.split("\\|")[0];
		Command command = provider.getCommand(commandName);
		String response;
		response = command.execute(request);
		return response;
	}
}
