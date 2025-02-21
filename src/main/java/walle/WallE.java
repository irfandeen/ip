package walle;

import java.util.Scanner;

public class WallE {
    private static final int MAX_LIST_SIZE = 100;
    private static final String exitMessage = "Bye. Hope to see you again soon!";
    private static final String greeting = "Hello! I'm Wall-E!\n" + "\tWhat can I do for you?\n\n";
    private static int listSize = 0;

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
        printWithLineBreak(greeting);
    }

    public static void printExitMessage() {
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
        Task[] tasks = new Task[MAX_LIST_SIZE];

        // Echoes the input, unless input == bye
        while (!userInput.equals("bye")) {
            String[] params = userInput.split(" ");
            int taskIndex = 0;
            processInput(params, tasks, userInput);
            userInput = reader.nextLine();
        }

        printExitMessage();
        reader.close();
    }

    private static void processInput(String[] params, Task[] tasks, String userInput) {
        try {
            switch (params[0]) {
            case "list":
                printTaskList(tasks, listSize);
                break;

            case "mark":
                markTask(params, tasks);
                break;

            case "unmark":
                unmarkTask(params, tasks);
                break;

            case "todo":
                addTodo(tasks, params, userInput);
                break;

            case "deadline":
                addDeadline(tasks, params);
                break;

            case "event":
                addEvent(tasks, params);
                break;

            default:
                throw new WallEException("Invalid command");
            }
        } catch(IndexOutOfBoundsException e) {
            printWithLineBreak("Invalid number of arguments.");
        } catch (WallEException e) {
            printWithLineBreak(e.getMessage());
        }
    }

    private static void addTask(Task[] tasks, String userInput) {
        tasks[listSize] = new Task(userInput);
        listSize++;
        printWithLineBreak("added: " + userInput);
    }

    private static void addEvent(Task[] tasks, String[] params) {
        int fromIndex = 0;
        int toIndex = 0;

        StringBuilder eventName = new StringBuilder();
        StringBuilder startDate = new StringBuilder();
        StringBuilder endDate = new StringBuilder();
        int stringType = 0;

        for (int i = 1; i < params.length; i++) {
            if (params[i].equals("/from")) {
                stringType = 1;
            } else if (params[i].equals("/to")) {
                stringType = 2;
            } else {
                if (stringType == 0) {
                    if (eventName.isEmpty()) {
                        eventName.append(params[i]);
                    } else {
                        eventName.append(" ").append(params[i]);
                    }
                } else if (stringType == 1) {
                    if (startDate.isEmpty()) {
                        startDate.append(params[i]);
                    } else {
                        startDate.append(" ").append(params[i]);
                    }
                } else {
                    if (endDate.isEmpty()) {
                        endDate.append(params[i]);
                    } else {
                        endDate.append(" ").append(params[i]);
                    }
                }
            }
        }

        tasks[listSize] = new Event(eventName.toString(), startDate.toString(), endDate.toString());
        listSize++;
        printWithLineBreak("added: " + tasks[listSize - 1].toString());
    }

    private static void addDeadline(Task[] tasks, String[] params) {
        StringBuilder deadlineName = new StringBuilder();
        StringBuilder byDate = new StringBuilder();
        int stringType = 0;

        for (int i = 1; i < params.length; i++) {
            if (params[i].equals("/by")) {
                stringType = 2;
            } else {
                if (stringType == 0) {
                    if (!deadlineName.isEmpty()) {
                        deadlineName.append(" ").append(params[i]);
                    } else {
                        deadlineName.append(params[i]);
                    }

                } else if (stringType == 2) {
                    if (!byDate.isEmpty()) {
                        byDate.append(" ").append(params[i]);
                    } else {
                        byDate.append(params[i]);
                    }
                }
            }
        }

        tasks[listSize] = new Deadline(deadlineName.toString(), byDate.toString());
        listSize++;
        printWithLineBreak(tasks[listSize - 1].toString());
    }

    private static void addTodo(Task[] tasks, String[] params, String userInput) {
        tasks[listSize] = new Todo(params[1]);
        listSize++;
        printWithLineBreak("added: " + userInput);
    }

    private static void unmarkTask(String[] params, Task[] tasks) {
        int taskIndex;
        taskIndex = Integer.parseInt(params[1]);
        tasks[taskIndex - 1].unmarkAsDone();
        printWithLineBreak("OK, I've marked this task as not done yet:\n"
                            + "\t" + tasks[taskIndex - 1]);
    }

    private static void markTask(String[] params, Task[] tasks) {
        int taskIndex;
        taskIndex = Integer.parseInt(params[1]);
        tasks[taskIndex - 1].markAsDone();
        printWithLineBreak("Nice! I've marked this task as done:\n"
                            + "\t" + tasks[taskIndex - 1]);
    }
}
