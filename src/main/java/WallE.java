import java.util.Scanner;

public class WallE {
    public static void printLineBreak() {
        String lineBreak = "\t_________________________________\n";
        System.out.print(lineBreak);
    }

    public static void printWithLineBreak(String response) {
        printLineBreak();
        System.out.println("\t" + response);
        printLineBreak();
    }

    public static void printWithoutLineBreak(String response) {
        System.out.println("\t" + response);
    }

    public static void printGreeting() {
        String greeting = "Hello! I'm Wall-E!\n" + "\tWhat can I do for you?\n\n";
        printWithLineBreak(greeting);
    }

    public static void printExit() {
        String exitMessage = "Bye. Hope to see you again soon!";
        printWithLineBreak(exitMessage);
    }

    public static void printTaskList(Task[] tasks, int size) {
        printLineBreak();
        printWithoutLineBreak("Here are the tasks in your list: ");
        for (int i = 0; i < size; i++) {
            printWithoutLineBreak(Integer.toString(i + 1) + ". [" + tasks[i].getStatusIcon() + "] "
                    + tasks[i].description);
        }
        printLineBreak();
    }

    public static void main(String[] args) {
        printGreeting();
        Scanner reader = new Scanner(System.in);
        String userInput = reader.nextLine();
        Task[] tasks = new Task[100];
        int size = 0;

        // Echoes the input, unless input == bye
        while (!userInput.equals("bye")) {
            String[] params = userInput.split(" ");
            int taskIndex = 0;

            switch (params[0]) {
            case "list":
                printTaskList(tasks, size);
                break;
            case "mark":
                taskIndex = Integer.parseInt(params[1]);
                tasks[taskIndex - 1].markAsDone();
                printWithLineBreak("Nice! I've marked this task as done:\n"
                                    + "\t  [" + tasks[taskIndex - 1].getStatusIcon() + "] "
                                    + tasks[taskIndex - 1].description);
                break;
            case "unmark":
                taskIndex = Integer.parseInt(params[1]);
                tasks[taskIndex - 1].unmarkAsDone();
                printWithLineBreak("OK, I've marked this task as not done yet:\n"
                                    + "\t  " + "[ ] " + tasks[taskIndex - 1].description);
                break;
            default:
                tasks[size] = new Task(userInput);
                size++;
                printWithLineBreak("added: " + userInput);
            }

            userInput = reader.nextLine();
        }

        printExit();
    }
}
