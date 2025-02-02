import java.util.Scanner;

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
        String inputCommand = reader.nextLine();

        // Echoes the input, unless input == bye
        while (!inputCommand.equals("bye")) {
            System.out.print(lineBreak + '\t' + inputCommand + '\n' + lineBreak);
            inputCommand = reader.nextLine();
        }

        System.out.print(exitMessage);
    }
}
