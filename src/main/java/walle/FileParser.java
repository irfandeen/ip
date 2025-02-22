package walle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileParser {
    public static Task[] readFileContents(String filePath, Integer listSize) throws FileNotFoundException {
        Task[] tasks = new Task[100];
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] taskString = s.nextLine().split(",");
            boolean isDone = taskString[0].equals("1") ? true : false;
            String taskType = taskString[1];
            String taskDescription = taskString[2];

            if (taskType.equals("T")) {
                tasks[listSize] = new Todo(taskDescription);
            } else if (taskType.equals("D")) {
                String dueDate = taskString[3];
                tasks[listSize] = new Deadline(taskDescription, dueDate);
            } else if (taskType.equals("E")) {
                String fromDate = taskString[3];
                String toDate = taskString[4];
                tasks[listSize] = new Event(taskDescription, fromDate, toDate);
            }
            listSize++;
        }
        s.close();
        return tasks;
    }

    private static void createFile(String filePath) {
        try {
            File f = new File(filePath);
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
                FileWriter writer = new FileWriter(f);
                writer.close();
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }
    }

    public FileParser(String filePath) {
        File f = new File(filePath);
        if (!f.exists()) {
            createFile(filePath);
        }

        try {
            System.out.println("Reading file: " + f.getName());
            readFileContents(filePath, 0);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void saveToFile(String filePath, Task[] tasks, int listSize) {
        File f = new File(filePath);
        if (!f.exists()) {
            createFile(filePath);
        }

        try {
            FileWriter writer = new FileWriter(f);
            for (int i = 0; i < listSize; i++) {
                Task t = tasks[i];
                String status = t.isDone() ? "1" : "0";
                writer.write(status + "," + t.getTypeIcon() + "," + t.getDescription());
                if (t.getTypeIcon() == "T") {
                    writer.write("\n");
                } else if (t.getTypeIcon() == "D") {
                    Deadline d = (Deadline) t;
                    writer.write("," + d.getDueDate() + "\n");
                } else if (t.getTypeIcon() == "E") {
                    Event e = (Event) t;
                    writer.write("," + e.getStartDate() + "," + e.getEndDate() + "\n");
                }
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("An error occurred while saving the file.");
            System.out.println(e.getMessage());
        }
    }
}
