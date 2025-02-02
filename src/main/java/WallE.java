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
        int size = 0;

        // Echoes the input, unless input == bye
        while (!userInput.equals("bye")) {
            String[] params = userInput.split(" ");
            switch (params[0]) {
            case "list":
                int count = 0;
                System.out.print(lineBreak);
                for (int i = 0; i < size; i++) {
                    System.out.println('\t' + Integer.toString(i + 1) + ". " + tasks[i]);
                }
                System.out.print(lineBreak);
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
