import java.util.Scanner;
import java.util.ArrayList;

public class WallE {
    public static void main(String[] args) {
        String lineBreak = "\t_________________________________\n";
        String greeting = lineBreak
                + "\tHello! I'm Wall-E!\n"
                + "\tWhat can I do for you?\n\n"
                + lineBreak;
        String exitMessage = lineBreak + "\tBye. Hope to see you again soon!\n" + lineBreak;

        System.out.print(greeting);
        Scanner reader = new Scanner(System.in);
        String userInput = reader.nextLine();
        String[] tasks = new String[100];
        boolean[] isDone = new boolean[100];
        int size = 0;

        // Echoes the input, unless input == bye
        while (!userInput.equals("bye")) {
            String[] params = userInput.split(" ");
            int taskIndex = 0;
            switch (params[0]) {
            case "list":
                int count = 0;
                System.out.print(lineBreak);
                for (int i = 0; i < size; i++) {
                    if (isDone[i]) {
                        System.out.println('\t' + Integer.toString(i + 1) + ". " + "[X] " + tasks[i]);
                    } else {
                        System.out.println('\t' + Integer.toString(i + 1) + ". " + "[ ] " + tasks[i]);
                    }
                }
                System.out.print(lineBreak);
                break;
            case "mark":
                taskIndex = Integer.parseInt(params[1]);
                System.out.print(lineBreak);
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t  " + "[X] " + tasks[taskIndex - 1]);
                System.out.print(lineBreak);
                isDone[taskIndex - 1] = true;
                break;
            case "unmark":
                taskIndex = Integer.parseInt(params[1]);
                System.out.print(lineBreak);
                System.out.println("\tOK, I've marked this task as not done yet:");
                System.out.println("\t  " + "[ ] " + tasks[taskIndex - 1]);
                System.out.print(lineBreak);
                isDone[taskIndex - 1] = false;
                break;
            default:
                tasks[size] = userInput;
                size++;
                System.out.print(lineBreak + '\t' + "added: " + userInput + '\n' + lineBreak);
            }

            userInput = reader.nextLine();
        }
        System.out.print(exitMessage);
    }
}
