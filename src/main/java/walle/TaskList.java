package walle;

import walle.task.Deadline;
import walle.task.Event;
import walle.task.Task;
import walle.task.Todo;

import java.time.LocalDate;
import java.time.LocalTime;
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
        Task deletedTask = tasks.remove(taskIndex - 1);
        listSize = getListSize() - 1;
        ui.printWithLineBreak("Deleted task: " + deletedTask.toString());
    }

    public void addEvent(String eventName, String startDate, String endDate) {
        tasks.add(new Event(eventName, startDate, endDate));
        listSize = getListSize() + 1;
        ui.printWithLineBreak("Added: " + tasks.get(getListSize() - 1).toString());
    }

    public void addDeadline(String taskName, LocalDate dueDate, LocalTime deadlineTime) {
        tasks.add(new Deadline(taskName, dueDate, deadlineTime));
        listSize = getListSize() + 1;
        ui.printWithLineBreak("Added: " + tasks.get(getListSize() - 1).toString());
    }

    public void addTodo(String todoTask) {
        tasks.add(new Todo(todoTask));
        listSize = getListSize() + 1;
        ui.printWithLineBreak("Added: " + tasks.get(getListSize() - 1).toString());
    }

    public void unmarkTask(int taskIndex) {
        tasks.get(taskIndex - 1).unmarkAsDone();
        ui.printWithLineBreak("OK, I've marked this task as not done yet:\n"
                + "\t" + tasks.get(taskIndex - 1).toString());
    }

    public void markTask(int taskIndex) {
        tasks.get(taskIndex - 1).markAsDone();
        ui.printWithLineBreak("Nice! I've marked this task as done:\n" + "\t" + tasks.get(taskIndex - 1));
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
