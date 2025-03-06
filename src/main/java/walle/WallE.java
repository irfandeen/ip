package walle;

import walle.command.CommandHandler;
import walle.command.CommandParser;
import walle.command.CommandType;

import walle.exception.InvalidCommandException;
import walle.exception.InvalidCommandParameterException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class WallE {
    private static final String FILE_NAME = "data.txt";
    private UserInterface ui;
    private TaskList tasks;
    private Storage storage;
    private CommandParser parser;
    private CommandHandler handler;

    private void run() {
        ui.printGreeting();
        storage = new Storage(FILE_NAME);
        try {
            tasks.setListSize(storage.readFileContents(FILE_NAME, tasks.getTasks()));
        } catch (FileNotFoundException e) {
            ui.printWithLineBreak("Error: Unable to create file.");
            return;
        }

        ui.printWithLineBreak("Stored list size: " + tasks.getListSize());
        Scanner reader = new Scanner(System.in);
        String userInput = reader.nextLine();

        while (!userInput.equals("bye")) {
            String[] params = userInput.split(" ");

            CommandType commandType = null;
            try {
                commandType = parser.getCommandType(params);
                ArrayList<String> arguments = parser.extractArguments(commandType, params);
                handler.handleCommand(commandType, arguments, tasks);
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            } catch (InvalidCommandParameterException e) {
                System.out.println(e.getMessage());
            }

            userInput = reader.nextLine();
        }

        storage.saveToFile("data.txt", tasks.getTasks(), tasks.getListSize());
        ui.printWithoutLineBreak("Saved data to " + FILE_NAME);
        ui.printExitMessage();
        reader.close();
    }

    public static void main(String[] args) {
        WallE wallE = new WallE();
        wallE.run();
    }

    public WallE() {
        ui = new UserInterface();
        tasks = new TaskList();
        parser = new CommandParser();
    }
}