package walle;

public class UserInterface {
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String GREETING = "Hello! I'm Wall-E!\n" + "\tWhat can I do for you?\n\n";

    public void printLineBreak() {
        String lineBreak = "\t_________________________________\n";
        System.out.print(lineBreak);
    }

    public void printWithLineBreak(String response) {
        printLineBreak();
        System.out.println("\t" + response);
        printLineBreak();
    }

    public void printWithoutLineBreak(String response) {
        System.out.println("\t" + response);
    }

    public void printGreeting() {
        printWithLineBreak(GREETING);
    }

    public void printExitMessage() {
        printWithLineBreak(EXIT_MESSAGE);
    }

    public UserInterface() { }
}
