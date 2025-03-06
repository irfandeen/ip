package walle.command;

import walle.exception.InvalidCommandException;
import walle.exception.InvalidCommandParameterException;

import java.util.ArrayList;

public class CommandParser {
    public CommandType getCommandType(String[] command) throws InvalidCommandException {
        CommandType commandType = null;
        switch (command[0]) {
        case "list":
            commandType = CommandType.LIST;
            break;

        case "mark":
            commandType = CommandType.MARK;
            break;

        case "unmark":
            commandType = CommandType.UNMARK;
            break;

        case "todo":
            commandType = CommandType.TODO;
            break;

        case "deadline":
            commandType = CommandType.DEADLINE;
            break;

        case "event":
            commandType = CommandType.EVENT;
            break;

        case "delete":
            commandType = CommandType.DELETE;
            break;

        default:
            commandType = null;
        }

        if (commandType == null) {
            throw new InvalidCommandException("Invalid command type");
        }
        return commandType;
    }

    public ArrayList<String> extractArguments(CommandType commandType, String[] params) throws InvalidCommandParameterException {
        ArrayList<String> arguments = new ArrayList<>();

        return arguments;
    }
}
