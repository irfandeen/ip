package walle;

import walle.task.Deadline;
import walle.task.Event;
import walle.task.Task;
import walle.task.Todo;

import java.util.ArrayList;

public class TaskList {
    private int listSize;
    private ArrayList<Task> tasks;
    private UserInterface ui;

    public void printTaskList() {
        ui.printLineBreak();
        ui.printWithoutLineBreak("Here are the tasks in your list: ");
        for (int i = 0; i < listSize; i++) {
            ui.printWithoutLineBreak(Integer.toString(i + 1) + ". " + tasks.get(i).toString());
        }
        ui.printLineBreak();
    }

    public void deleteTask(int taskIndex) {
        Task deletedTask = tasks.remove(taskIndex);
        listSize = getListSize() - 1;
        ui.printWithLineBreak("deleted task: " + deletedTask.toString());
    }

    public void addEvent(String[] params) {
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
        listSize = getListSize() + 1;
        ui.printWithLineBreak("added: " + tasks.get(getListSize() - 1).toString());
    }

    public void addDeadline(String[] params) {
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
        listSize = getListSize() + 1;
        ui.printWithLineBreak(tasks.get(getListSize() - 1).toString());
    }

    public void addTodo(String[] params, String userInput) {
        tasks.add(new Todo(params[1]));
        listSize = getListSize() + 1;
        ui.printWithLineBreak("added: " + tasks.get(getListSize() - 1).toString());
    }

    public void unmarkTask(String[] params) {
        int taskIndex;
        taskIndex = Integer.parseInt(params[1]);
        tasks.get(taskIndex - 1).unmarkAsDone();
        ui.printWithLineBreak("OK, I've marked this task as not done yet:\n"
                + "\t" + tasks.get(taskIndex - 1).toString());
    }

    public void markTask(String[] params) {
        int taskIndex;
        taskIndex = Integer.parseInt(params[1]);
        tasks.get(taskIndex - 1).markAsDone();
        ui.printWithLineBreak("Nice! I've marked this task as done:\n"
                + "\t" + tasks.get(taskIndex - 1));
    }

    public TaskList() {
        listSize = 0;
        tasks = new ArrayList<>();
        ui = new UserInterface();
    }

    public int getListSize() {
        return listSize;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setListSize(int listSize) {
        this.listSize = listSize;
    }
}
