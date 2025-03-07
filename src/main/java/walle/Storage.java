package walle;

import walle.task.Deadline;
import walle.task.Event;
import walle.task.Task;
import walle.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles reading from and writing to a storage file for tasks.
 * Provides methods to load tasks from a file and save tasks to a file.
 */
public class Storage {
    private File file;

    /**
     * Reads the contents of the specified file and loads tasks into the provided list.
     *
     * @param filePath The path to the file to read from.
     * @param tasks The list to store the loaded tasks.
     * @return The number of tasks read from the file.
     * @throws FileNotFoundException If the file cannot be found.
     */
    public int readFileContents(String filePath, ArrayList<Task> tasks) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int listSize = 0;
        while (scanner.hasNext()) {
            String[] taskString = scanner.nextLine().split(",");
            boolean isDone = taskString[0].equals("1") ? true : false;
            String taskType = taskString[1];
            String taskDescription = taskString[2];

            if (taskType.equals("T")) {
                tasks.add(new Todo(taskDescription));
            } else if (taskType.equals("D")) {
                String dueDate = taskString[3];
                String dueTime = taskString[4];
                tasks.add(new Deadline(taskDescription, dueDate, dueTime));
            } else if (taskType.equals("E")) {
                String fromDate = taskString[3];
                String toDate = taskString[4];
                tasks.add(new Event(taskDescription, fromDate, toDate));
            }

            if (isDone) {
                tasks.get(listSize).markAsDone();
            }

            listSize++;
        }
        scanner.close();
        return listSize;
    }

    private void createFile(String filePath) {
        try {
            File tempFile = new File(filePath);
            if (tempFile.createNewFile()) {
                System.out.println("File created: " + tempFile.getName());
                FileWriter writer = new FileWriter(tempFile);
                writer.close();
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
        }
    }

    public Storage(String filePath) {
        file = new File(filePath);
        if (!file.exists()) {
            createFile(filePath);
        }
    }

    /**
     * Saves the tasks to the file at the specified path.
     *
     * @param filePath The path to the file to save to.
     * @param tasks The list of tasks to save.
     * @param listSize The number of tasks to save.
     */
    public void saveToFile(String filePath, ArrayList<Task> tasks, int listSize) {
        try {
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < listSize; i++) {
                Task t = tasks.get(i);
                String status = t.isDone() ? "1" : "0";
                writer.write(status + "," + t.getTypeIcon() + "," + t.getDescription());
                if (t.getTypeIcon() == "T") {
                    writer.write("\n");
                } else if (t.getTypeIcon() == "D") {
                    Deadline d = (Deadline) t;
                    writer.write("," + d.getDueDate() + "," + d.getDueTime() + "\n");
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
