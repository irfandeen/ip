package walle.command;

import java.util.ArrayList;
import walle.TaskList;
import walle.exception.InvalidCommandParameterException;

public class CommandHandler {
    public void handleCommand(CommandType commandType, ArrayList<String> arguments, TaskList tasks) throws InvalidCommandParameterException {
        switch (commandType) {
        case LIST:
            handleListCommand(tasks);
            break;
            default:
                System.out.println("Not yet implemented");
        }
    }

    private void handleListCommand(TaskList tasks) {
        tasks.printTaskList();
    }
}
