package walle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WallE {
    private static final int MAX_LIST_SIZE = 100;
    private static final String exitMessage = "Bye. Hope to see you again soon!";
    private static final String greeting = "Hello! I'm Wall-E!\n" + "\tWhat can I do for you?\n\n";
    private static int listSize = 0;
    private static final String FILE_NAME = "data.txt";
    private static ArrayList<Task> tasks = new ArrayList<>();

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

    public static void printTaskList(int size) {
        printLineBreak();
        printWithoutLineBreak("Here are the tasks in your list: ");
        for (int i = 0; i < size; i++) {
            printWithoutLineBreak(Integer.toString(i + 1) + ". " + tasks.get(i).toString());
        }
        printLineBreak();
    }

    public static void main(String[] args) throws IOException {
        printGreeting();
        FileParser parser = new FileParser("data.txt");
        listSize = parser.readFileContents(FILE_NAME, tasks);
        printWithLineBreak("Stored list size: " + listSize);
        Scanner reader = new Scanner(System.in);
        String userInput = reader.nextLine();

        // Echoes the input, unless input == bye
        while (!userInput.equals("bye")) {
            String[] params = userInput.split(" ");
            int taskIndex = 0;
            processInput(params, tasks, userInput);
            userInput = reader.nextLine();
        }

        //putIntoStoredTasks(tasks);
        parser.saveToFile("data.txt", tasks, listSize);
        printWithoutLineBreak("Saved data to " + FILE_NAME);
        printExitMessage();
        reader.close();
    }

    private static void processInput(String[] params, ArrayList<Task> tasks, String userInput) {
        try {
            switch (params[0]) {
            case "list":
                printTaskList(listSize);
                break;

            case "mark":
                markTask(params);
                break;

            case "unmark":
                unmarkTask(params);
                break;

            case "todo":
                addTodo(params, userInput);
                break;

            case "deadline":
                addDeadline(params);
                break;

            case "event":
                addEvent(params);
                break;

            case "delete":
                int taskIndex = Integer.parseInt(params[1]);
                deleteTask(taskIndex - 1); // 1 indexed
                break;

            default:
                throw new WallEException("Invalid command");
            }
        } catch(IndexOutOfBoundsException e) {
            printWithLineBreak("Invalid command, please ensure correct arguments.");
        } catch (WallEException e) {
            printWithLineBreak(e.getMessage());
        }
    }

    private static void addTask(String userInput) {
        tasks.add(new Task(userInput));
        listSize++;
        printWithLineBreak("added: " + userInput);
    }

    private static void deleteTask(int taskIndex) {
        Task deletedTask = tasks.remove(taskIndex);
        listSize--;
        printWithLineBreak("deleted task: " + deletedTask.toString());
    }

    private static void addEvent(String[] params) {
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

        tasks.add(new Event(eventName.toString(), startDate.toString(), endDate.toString()));
        listSize++;
        printWithLineBreak("added: " + tasks.get(listSize - 1).toString());
    }

    private static void addDeadline(String[] params) {
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

        tasks.add(new Deadline(deadlineName.toString(), byDate.toString()));
        listSize++;
        printWithLineBreak(tasks.get(listSize - 1).toString());
    }

    private static void addTodo(String[] params, String userInput) {
        tasks.add(new Todo(params[1]));
        listSize++;
        printWithLineBreak("added: " + tasks.get(listSize - 1).toString());
    }

    private static void unmarkTask(String[] params) {
        int taskIndex;
        taskIndex = Integer.parseInt(params[1]);
        tasks.get(taskIndex - 1).unmarkAsDone();
        printWithLineBreak("OK, I've marked this task as not done yet:\n"
                            + "\t" + tasks.get(taskIndex - 1).toString());
    }

    private static void markTask(String[] params) {
        int taskIndex;
        taskIndex = Integer.parseInt(params[1]);
        tasks.get(taskIndex - 1).markAsDone();
        printWithLineBreak("Nice! I've marked this task as done:\n"
                            + "\t" + tasks.get(taskIndex - 1));
    }
}
