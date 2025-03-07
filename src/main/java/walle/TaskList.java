package walle;

import walle.task.Deadline;
import walle.task.Event;
import walle.task.Task;
import walle.task.Todo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Represents a list of tasks, with methods for managing tasks such as adding, removing,
 * marking/unmarking, and displaying them.
 */
public class TaskList {
    private int listSize;
    private ArrayList<Task> tasks;
    private UserInterface ui;

    /**
     * Prints the list of tasks to the user interface.
     */
    public void printTaskList() {
        ui.printLineBreak();
        ui.printWithoutLineBreak("Here are the tasks in your list: ");
        for (int i = 0; i < listSize; i++) {
            ui.printWithoutLineBreak(Integer.toString(i + 1) + ". " + tasks.get(i).toString());
        }
        ui.printLineBreak();
    }

    /**
     * Deletes a task from the list by its index.
     *
     * @param taskIndex The index of the task to delete.
     */
    public void deleteTask(int taskIndex) {
        Task deletedTask = tasks.remove(taskIndex - 1);
        listSize = getListSize() - 1;
        ui.printWithLineBreak("Deleted task: " + deletedTask.toString());
    }

    /**
     * Adds an event task to the task list.
     *
     * @param eventName The name/description of the event.
     * @param startDate The start date of the event.
     * @param endDate The end date of the event.
     */
    public void addEvent(String eventName, String startDate, String endDate) {
        tasks.add(new Event(eventName, startDate, endDate));
        listSize = getListSize() + 1;
        ui.printWithLineBreak("Added: " + tasks.get(getListSize() - 1).toString());
    }

    /**
     * Adds a deadline task to the task list.
     *
     * @param taskName The name/description of the task.
     * @param dueDate The due date of the task.
     */
    public void addDeadline(String taskName, LocalDate dueDate, LocalTime deadlineTime) {
        tasks.add(new Deadline(taskName, dueDate, deadlineTime));
        listSize = getListSize() + 1;
        ui.printWithLineBreak("Added: " + tasks.get(getListSize() - 1).toString());
    }

    /**
     * Adds a to-do task to the task list.
     *
     * @param todoTask The name/description of the to-do task.
     */
    public void addTodo(String todoTask) {
        tasks.add(new Todo(todoTask));
        listSize = getListSize() + 1;
        ui.printWithLineBreak("Added: " + tasks.get(getListSize() - 1).toString());
    }

    /**
     * Unmarks a task as done.
     *
     * @param taskIndex The index of the task to unmark.
     */
    public void unmarkTask(int taskIndex) {
        tasks.get(taskIndex - 1).unmarkAsDone();
        ui.printWithLineBreak("OK, I've marked this task as not done yet:\n"
                + "\t" + tasks.get(taskIndex - 1).toString());
    }

    /**
     * Marks a task as done.
     *
     * @param taskIndex The index of the task to mark.
     */
    public void markTask(int taskIndex) {
        tasks.get(taskIndex - 1).markAsDone();
        ui.printWithLineBreak("Nice! I've marked this task as done:\n" + "\t" + tasks.get(taskIndex - 1));
    }

    /**
     * Constructs an empty TaskList with no {@link Task} stored.
     */
    public TaskList() {
        listSize = 0;
        tasks = new ArrayList<>();
        ui = new UserInterface();
    }

    /**
     * Returns the current size of the task list.
     *
     * @return The number of tasks in the list.
     */
    public int getListSize() {
        return listSize;
    }

    /**
     * Returns the list of tasks.
     *
     * @return An ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Sets the size of the task list.
     *
     * @param listSize The new size of the task list.
     */
    public void setListSize(int listSize) {
        this.listSize = listSize;
    }
}
