package walle.task;

/**
 * Represents a general task with a description and completion status.
 * This is the base class for other specific types of tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with the specified description and initializes its status as incomplete.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon representing the completion status of the task.
     *
     * @return A string representing the task's completion status ("[X]" for done, "[ ]" for not done).
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Returns the type of the task as a string.
     *
     * @return A string representing the task type ("[ ]").
     */
    public String getType() {
        return "[ ]";
    }

    /**
     * Returns the type icon for the task.
     *
     * @return A string representing the type icon (" ").
     */
    public String getTypeIcon() {
        return " ";
    }

    /**
     * Returns the description of the task.
     *
     * @return The description as a string.
     */
    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getType() + getStatusIcon() + " " + description;
    }
}
