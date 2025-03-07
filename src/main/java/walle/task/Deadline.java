package walle.task;
/**
 * Represents a task with a deadline.
 * Inherits from the {@link Task} class and adds a due date.
 */
public class Deadline extends Task {
    protected String dueDate;

    /**
     * Constructs a Deadline task with a description and due date.
     *
     * @param description The description of the task.
     * @param dueDate The due date of the task.
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Returns the type of the task as a string.
     *
     * @return A string representing the task type ("[D]").
     */
    @Override
    public String getType() {
        return "[D]";
    }

    /**
     * Returns the type icon for the task.
     *
     * @return A string representing the type icon ("D").
     */
    public String getTypeIcon() {
        return "D";
    }

    /**
     * Returns the due date of the task.
     *
     * @return The due date as a string.
     */
    public String getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + dueDate + ")";
    }
}
