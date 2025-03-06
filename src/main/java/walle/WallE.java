package walle;

import walle.command.CommandParser;
import walle.command.CommandType;

import walle.exception.InvalidCommandException;
import walle.exception.InvalidCommandParameterException;
import walle.exception.WallEException;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class WallE {
    private static final String FILE_NAME = "data.txt";
    private UserInterface ui;
    private TaskList tasks;
    private Storage storage;
    private CommandParser parser;

    private void handleUserInput(String[] params) {
        CommandType commandType = null;
        try {
            commandType = parser.getCommandType(params);
            parser.handleCommand(commandType, params, tasks);
        } catch (InvalidCommandException e) {
            ui.printWithLineBreak(e.getMessage());
        } catch (InvalidCommandParameterException e) {
            ui.printWithLineBreak(e.getMessage());
        }
    }

    private void initialiseWallE() throws WallEException {
        ui.printGreeting();
        storage = new Storage(FILE_NAME);
        try {
            tasks.setListSize(storage.readFileContents(FILE_NAME, tasks.getTasks()));
        } catch (FileNotFoundException e) {
            throw new WallEException("Error: Unable to create storage file.");
        }
    }

    private void exitWallE() {
        storage.saveToFile("data.txt", tasks.getTasks(), tasks.getListSize());
        ui.printWithoutLineBreak("Saved data to " + FILE_NAME);
        ui.printExitMessage();
    }

    private void run() throws WallEException {
        initialiseWallE();

        ui.printWithLineBreak("Stored list size: " + tasks.getListSize());
        Scanner reader = new Scanner(System.in);
        String userInput = reader.nextLine();
        while (!userInput.equals("bye")) {
            String[] params = userInput.split(" ");
            handleUserInput(params);
            userInput = reader.nextLine();
        }

        exitWallE();
        reader.close();
    }

    public WallE() {
        ui = new UserInterface();
        tasks = new TaskList();
        parser = new CommandParser();
    }

    public static void main(String[] args) {
        WallE wallE = new WallE();
        try {
            wallE.run();
        } catch (WallEException e) {
            System.out.println("\t" + e.getMessage());
        }
    }
}