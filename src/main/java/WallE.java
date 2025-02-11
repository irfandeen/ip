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
            printWithoutLineBreak(Integer.toString(i + 1) + ". " + tasks[i]);
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
                                    + "\t" + tasks[taskIndex - 1]);
                break;

            case "unmark":
                taskIndex = Integer.parseInt(params[1]);
                tasks[taskIndex - 1].unmarkAsDone();
                printWithLineBreak("OK, I've marked this task as not done yet:\n"
                                    + "\t" + tasks[taskIndex - 1]);
                break;

            case "todo":
                tasks[size] = new Todo(params[1]);
                size++;
                printWithLineBreak("added: " + userInput);
                break;

            case "deadline":
                tasks[size] = new Deadline(params[1], params[2]);
                size++;
                printWithLineBreak("added: " + params[1] + " " + params[2]);
                break;

            case "event":
                tasks[size] = new Event(params[1], params[2], params[3]);
                size++;
                printWithLineBreak("added: " + params[1] + " " + params[2] + params[3]);
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
