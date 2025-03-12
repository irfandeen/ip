package walle;

import java.util.Scanner;

/**
 * Represents the user interface of the Wall-E application. Provides methods to print messages
 * with specific formatting like line breaks and greetings.
 */
public class UserInterface {
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String GREETING = "Hello! I'm Wall-E!\n" + "\tWhat can I do for you?\n\n";
    private Scanner scanner;

    /**
     * Prints a line break for formatting purposes.
     */
    public void printLineBreak() {
        String lineBreak = "\t_________________________________\n";
        System.out.print(lineBreak);
    }

    /**
     * Prints a message with line breaks before and after it.
     *
     * @param response The message to be printed.
     */
    public void printWithLineBreak(String response) {
        printLineBreak();
        System.out.println("\t" + response);
        printLineBreak();
    }

    /**
     * Prints a message without a line break after it.
     *
     * @param response The message to be printed.
     */
    public void printWithoutLineBreak(String response) {
        System.out.println("\t" + response);
    }

    /**
     * Prints a greeting message to the user.
     */
    public void printGreeting() {
        printWithLineBreak(GREETING);
    }

    /**
     * Prints the exit message when the user decides to exit the program.
     */
    public void printExitMessage() {
        printWithLineBreak(EXIT_MESSAGE);
    }

    /**
     * Gets next line of input from user.
     * @return Input from user in {@link String} format
     */
    public String getInput() {
        return scanner.nextLine();
    }

    /**
     *  Closes standard input to prevent memory leakage.
     */
    public void closeInput() {
        scanner.close();
    }

    /**
     * Constructs a new UserInterface object and instantiates standard input.
     */
    public UserInterface() {
        scanner = new Scanner(System.in);
    }
}
