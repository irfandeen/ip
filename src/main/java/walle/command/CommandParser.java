package walle.command;

import walle.TaskList;
import walle.UserInterface;
import walle.exception.InvalidCommandException;
import walle.exception.InvalidCommandParameterException;
import walle.task.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class CommandParser {
    private static final int LIST_COMMAND_LENGTH = 1;
    private static final int INDEX_COMMAND_LENGTH = 2;
    private static final int MIN_TODO_LENGTH = 2;
    private static final int MIN_DEADLINE_LENGTH = 4;
    private static final int MIN_EVENT_LENGTH = 6;

    public CommandType getCommandType(String[] command) throws InvalidCommandException {
        CommandType commandType;
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

    public void handleCommand(CommandType commandType, String[] params, TaskList tasks, UserInterface ui) throws InvalidCommandParameterException, InvalidCommandException {
        switch (commandType) {
        case LIST:
            handleListCommand(params, tasks);
            break;
        case MARK:
            handleMarkCommand(params, tasks);
            break;
        case UNMARK:
            handleUnmarkCommand(params, tasks);
            break;
        case TODO:
            handleTodoCommand(params, tasks);
            break;
        case DELETE:
            handleDeleteCommand(params, tasks);
            break;
        case DEADLINE:
            handleDeadlineCommand(params, tasks);
            break;
        case EVENT:
            handleEventCommand(params, tasks);
            break;
        default:
            throw new InvalidCommandException("Unknown command type");
        }
    }

    private void handleListCommand(String[] params, TaskList tasks) throws InvalidCommandParameterException {
        if (params.length != LIST_COMMAND_LENGTH) {
            throw new InvalidCommandParameterException("Invalid list command. List does not take any arguments.");
        }
        tasks.printTaskList();
    }

    private void handleMarkCommand(String[] params, TaskList tasks) throws InvalidCommandParameterException {
        if (params.length != INDEX_COMMAND_LENGTH) {
            throw new InvalidCommandParameterException("Invalid mark command. Mark takes index as argument.");
        }
        if (!isValidInteger(params[1])) {
            throw new InvalidCommandParameterException("Mark command expects an integer.");
        }
        if (!isValidIndex(params[1], tasks)) {
            throw new InvalidCommandParameterException("Mark command expects a valid index.");
        }

        int index = Integer.parseInt(params[1]);
        tasks.markTask(index);
    }

    private void handleUnmarkCommand(String[] params, TaskList tasks) throws InvalidCommandParameterException {
        if (params.length != INDEX_COMMAND_LENGTH) {
            throw new InvalidCommandParameterException("Invalid unmark command. Unmark takes index as argument.");
        }
        if (!isValidInteger(params[1])) {
            throw new InvalidCommandParameterException("Unmark command expects a number.");
        }
        if (!isValidIndex(params[1], tasks)) {
            throw new InvalidCommandParameterException("Unmark command expects a valid index.");
        }

        int index = Integer.parseInt(params[1]);
        tasks.unmarkTask(index);
    }

    private void handleTodoCommand(String[] params, TaskList tasks) throws InvalidCommandParameterException {
        if (params.length < MIN_TODO_LENGTH) {
            throw new InvalidCommandParameterException("Enter a todo task.");
        }

        String todoTask = String.join(" ", Arrays.copyOfRange(params, 1, params.length));
        tasks.addTodo(todoTask);
    }

    private void handleDeleteCommand(String[] params, TaskList tasks) throws InvalidCommandParameterException {
        if (params.length != INDEX_COMMAND_LENGTH) {
            throw new InvalidCommandParameterException("Delete takes index as argument.");
        }
        if (!isValidInteger(params[1])) {
            throw new InvalidCommandParameterException("Delete command expects a number.");
        }
        if (tasks.getListSize() == 0) {
            throw new InvalidCommandParameterException("Nothing to delete in task list.");
        }
        if (!isValidIndex(params[1], tasks)) {
            throw new InvalidCommandParameterException("Delete command expects a valid index.");
        }

        int taskIndex = Integer.parseInt(params[1]);
        tasks.deleteTask(taskIndex);
    }

    private void handleDeadlineCommand(String[] params, TaskList tasks) throws InvalidCommandParameterException {
        if (params.length < MIN_DEADLINE_LENGTH) {
            throw new InvalidCommandParameterException("Invalid deadline command. Deadline command: deadline <deadline task> /by <due date>");
        }

        int byIndex = Arrays.asList(params).indexOf("/by");
        if (byIndex == -1 || byIndex >= params.length - 2) {
            throw new InvalidCommandParameterException("Deadline command expects a valid due date and time.");
        }
        if (!isValidDate(params[byIndex + 1])) {
            throw new InvalidCommandParameterException("Deadline command expects a valid due date of the format dd-mm-yyyy HHMM.");
        }
        if (!isValidTime(params[byIndex + 2])) {
            throw new InvalidCommandParameterException("Deadline command expects a valid time of the format HH:MM");
        }

        String taskName = String.join(" ", Arrays.copyOfRange(params, 1, byIndex));
        String dueDateStr = params[byIndex + 1];
        String dueTimeStr = params[byIndex + 2];
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

        tasks.addDeadline(taskName, LocalDate.parse(dueDateStr, dateFormatter), LocalTime.parse(dueTimeStr, timeFormatter));
    }

    private void handleEventCommand(String[] params, TaskList tasks) throws InvalidCommandParameterException {
        if (params.length < MIN_EVENT_LENGTH) {
            throw new InvalidCommandParameterException("Invalid event format. Event command: event <event task> /from <start> /to <end>");
        }

        int fromIndex = Arrays.asList(params).indexOf("/from");
        int toIndex = Arrays.asList(params).indexOf("/to");

        if (fromIndex == -1 || toIndex == -1 || toIndex == params.length - 1 || toIndex == params.length - 1) {
            throw new InvalidCommandParameterException("Event command expects a valid start and end date time.");
        }
        if (toIndex < fromIndex || fromIndex + 1 == toIndex) {
            throw new InvalidCommandParameterException("Invalid event format. Event command: event <event task> /from <start> /to <end>");
        }
        if (fromIndex == 1) {
            throw new InvalidCommandParameterException("Event name expected.");
        }

        String eventName = String.join(" ", Arrays.copyOfRange(params, 1, fromIndex));
        String fromDate = String.join(" ", Arrays.copyOfRange(params, fromIndex + 1, toIndex));
        String toDate = String.join(" ", Arrays.copyOfRange(params, toIndex + 1, params.length));
        tasks.addEvent(eventName, fromDate, toDate);
    }

    private boolean isValidInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private boolean isValidIndex(String input, TaskList tasks) {
        int index = Integer.parseInt(input);
        return index >= 1 && index <= tasks.getListSize();
    }

    private boolean isValidDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(dateStr, formatter);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean isValidTime(String timeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        try {
            LocalTime.parse(timeStr, formatter);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
